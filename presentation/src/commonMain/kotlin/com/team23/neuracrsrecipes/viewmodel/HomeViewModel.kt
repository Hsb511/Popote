package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.favorite.usecase.UpdateFavoriteUseCase
import com.team23.domain.recipe.usecase.GetAllSummarizedRecipesUseCase
import com.team23.domain.recipe.usecase.GetFullRecipeByIdUseCase
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.SummarizedRecipeUiMapper
import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.component.getScopeName

class HomeViewModel(
    private val getAllSummarizedRecipesUseCase: GetAllSummarizedRecipesUseCase,
    private val getFullRecipeByIdUseCase: GetFullRecipeByIdUseCase,
    private val summarizedRecipeUiMapper: SummarizedRecipeUiMapper,
    private val updateFavoriteUseCase: UpdateFavoriteUseCase,
    private val viewModelScope: CoroutineScope,
    private val snackbarHandler: SnackbarHandler,
) {
    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            viewModelScope.launch(Dispatchers.IO) {
                snackbarHandler.showStartLoading()
            }
            getAllSummarizedRecipesUseCase.invoke()
                .onFailure {
                    withContext(Dispatchers.Main) {
                        _uiState.value = HomeUiState.Error(
                            errorUiModel = ErrorUiModel(
                                message = "${it.getScopeName()} ${it.message}",
                                redirectToWebsite = {},
                            )
                        )
                    }
                }.onSuccess { data ->
                    val recipes = data.first
                    val newRecipesCount = data.second
                    viewModelScope.launch(Dispatchers.IO) {
                        snackbarHandler.showLoadingRecipe(newRecipesCount)
                    }

                    withContext(Dispatchers.Main) {
                        _uiState.value = HomeUiState.Data(recipes = recipes.map {
                            summarizedRecipeUiMapper.toUiModel(it)
                        })
                    }
                    recipes.forEach { recipe ->
                        getFullRecipeByIdUseCase.invoke(recipe.id)
                    }
                    if (newRecipesCount != 0) {
                        snackbarHandler.showLoadingEnded()
                    }
                }
        }
    }

    fun favoriteClick(recipe: SummarizedRecipeUiModel) {
        viewModelScope.launch(Dispatchers.IO) {
            updateFavoriteUseCase.invoke(recipe.id)
            recomputeState(recipe.id)
            if (!recipe.isFavorite) {
                val result = snackbarHandler.showFavoriteMessage(recipe.title)
                if (result == SnackbarResultUiModel.ActionPerformed) {
                    updateFavoriteUseCase.invoke(recipe.id)
                    recomputeState(recipe.id)
                }
            }
        }
    }

    fun onLocalPhoneClick() {
        viewModelScope.launch(Dispatchers.IO) {
            snackbarHandler.showLocalPhoneMessage()
        }
    }

    private fun recomputeState(recipeId: String) {
        val currentState = _uiState.value
        if (currentState is HomeUiState.Data) {
            val newRecipes = currentState.recipes.toMutableList()
            val recipe = newRecipes.first { recipe -> recipe.id == recipeId }
            val recipeIndex = newRecipes.indexOf(recipe)
            newRecipes.remove(recipe)
            newRecipes.add(recipeIndex, recipe.copy(isFavorite = !recipe.isFavorite))
            _uiState.value = HomeUiState.Data(newRecipes)
        }
    }
}
