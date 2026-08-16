package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.domain.preference.usecase.GetPreferenceDisplayTypeUseCase
import com.team23.domain.preference.usecase.UpdatePreferenceUseCase
import com.team23.domain.recipe.usecase.GetFullRecipeByIdUseCase
import com.team23.neuracrsrecipes.extension.next
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.DisplayTypeUiMapper
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeUiMapper
import com.team23.neuracrsrecipes.mapper.TagUiMapper
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoriteViewModel(
    private val favoriteRepository: FavoriteRepository,
    private val groceryListRepository: GroceryListRepository,
    private val getPreferenceDisplayTypeUseCase: GetPreferenceDisplayTypeUseCase,
    private val updatePreferenceUseCase: UpdatePreferenceUseCase,
    private val getFullRecipeByIdUseCase: GetFullRecipeByIdUseCase,
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
    private val displayTypeUiMapper: DisplayTypeUiMapper,
    private val tagUiMapper: TagUiMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
    private val _uiState = MutableStateFlow<FavoriteUiState>(FavoriteUiState.Loading)
    val uiState: StateFlow<FavoriteUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val displayType =
                displayTypeUiMapper.toDisplayTypeUiModel(getPreferenceDisplayTypeUseCase.invoke())
            favoriteRepository.getAllFavorites().map { recipes ->
                recipes.map { summarizedRecipeUiMapper.toUiModel(it) }
            }.collect { favorites ->
                loadCuisineFlags(favorites)
                val currentDisplayType = (_uiState.value as? FavoriteUiState.Data.WithFavorites)?.displayType
                    ?: displayType

                _uiState.value = if (favorites.isEmpty()) {
                    FavoriteUiState.Data.Empty
                } else {
                    FavoriteUiState.Data.WithFavorites(
                        displayType = currentDisplayType,
                        favorites = favorites,
                    )
                }
            }
        }
    }

    private fun loadCuisineFlags(recipes: List<SummarizedRecipeUiModel>) {
        viewModelScope.launch(Dispatchers.IO) {
            val updatedRecipesWithCuisine = recipes.map { recipe ->
                val fullRecipe = getFullRecipeByIdUseCase.invoke(recipe.id).getOrNull()
                if (fullRecipe != null) {
                    recipe.copy(cuisineFlag = tagUiMapper.toFlagProperty(fullRecipe.tags),)
                } else {
                    recipe
                }
            }
            withContext(Dispatchers.Main) {
                _uiState.value = when (val currentState = _uiState.value) {
                    is FavoriteUiState.Data.WithFavorites -> currentState.copy(favorites = updatedRecipesWithCuisine)
                    else -> currentState
                }
            }
        }
    }

    fun onFavoriteClick(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.updateFavorite(recipeId)
        }
    }

    fun onDisplayTypeClick() {
        val currentState = _uiState.value
        if (currentState is FavoriteUiState.Data.WithFavorites) {
            val newDisplayType = currentState.displayType.next()
            _uiState.value = currentState.copy(
                displayType = newDisplayType,
            )
            viewModelScope.launch(Dispatchers.IO) {
                updatePreferenceUseCase.invoke(
                    displayTypeUiMapper.toDisplayTypeDomainModel(newDisplayType)
                )
            }
        }
    }

    fun onLocalPhoneClick() {
        viewModelScope.launch(Dispatchers.IO) {
            snackbarHandler.showLocalPhoneMessage()
        }
    }

    fun onRemoveAllConfirm() {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.clearAllFavorites()
        }
    }

    fun onToggleGroceryListClick(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val currentState = (_uiState as? FavoriteUiState.Data) as? FavoriteUiState.Data.WithFavorites
            val recipeTitle = currentState?.favorites?.find { it.id == recipeId }?.title.orEmpty()
            val isInGroceryList = groceryListRepository.toggleInGroceryList(recipeId)
            if (isInGroceryList) {
                val result = snackbarHandler.showGroceryListMessage(recipeTitle)
                if (result == SnackbarResultUiModel.ActionPerformed) {
                    groceryListRepository.toggleInGroceryList(recipeId)
                }
            }
        }
    }
}
