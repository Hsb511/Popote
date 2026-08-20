package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.favorite.usecase.GetAllFavoritesUseCase
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.domain.preference.repository.PreferenceRepository
import com.team23.domain.preference.usecase.UpdatePreferenceUseCase
import com.team23.neuracrsrecipes.extension.next
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.DisplayTypeUiMapper
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeUiMapper
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(
    getAllFavoritesUseCase: GetAllFavoritesUseCase,
    preferenceRepository: PreferenceRepository,
    private val favoriteRepository: FavoriteRepository,
    private val groceryListRepository: GroceryListRepository,
    private val updatePreferenceUseCase: UpdatePreferenceUseCase,
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
    private val displayTypeUiMapper: DisplayTypeUiMapper,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
    val uiState: StateFlow<FavoriteUiState> = combine(
        getAllFavoritesUseCase.invoke().map(summarizedRecipeUiMapper::toUiModels),
        preferenceRepository.getDisplayType().map(displayTypeUiMapper::toDisplayTypeUiModel)
    ) { favorites, displayType ->
        if (favorites.isEmpty()) {
            FavoriteUiState.Data.Empty
        } else {
            FavoriteUiState.Data.WithFavorites(
                displayType = displayType,
                favorites = favorites,
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Eagerly, FavoriteUiState.Loading)

    fun onFavoriteClick(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteRepository.updateFavorite(recipeId)
        }
    }

    fun onDisplayTypeClick() {
        val currentState = uiState.value
        if (currentState is FavoriteUiState.Data.WithFavorites) {
            val newDisplayType = currentState.displayType.next()
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
            val currentState = (uiState.value as? FavoriteUiState.Data) as? FavoriteUiState.Data.WithFavorites
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
