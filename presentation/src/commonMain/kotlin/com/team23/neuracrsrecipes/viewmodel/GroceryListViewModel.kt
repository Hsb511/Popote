package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.mapper.GroceryListUiMapper
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.action.GroceryListAction
import com.team23.neuracrsrecipes.model.event.GroceryUiEvent
import com.team23.neuracrsrecipes.model.uimodel.GroceryListUiModel
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroceryListViewModel(
    groceryListUiMapper: GroceryListUiMapper,
    val viewModelScope: CoroutineScope,
    val groceryListRepository: GroceryListRepository,
    val favoriteRepository: FavoriteRepository,
    private val snackbarHandler: SnackbarHandler,
) {
    val uiState: StateFlow<GroceryListUiModel> = groceryListRepository.getGroceryList()
        .map(groceryListUiMapper::toUiModel)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = GroceryListUiModel()
        )
    private val _uiEvent: MutableSharedFlow<GroceryUiEvent> = MutableSharedFlow()
    val uiEvent: SharedFlow<GroceryUiEvent> = _uiEvent

    fun onAction(action: GroceryListAction) {
        when (action) {
            is GroceryListAction.ChangeServingsAmount -> changeServingsAmount(action.recipeId, action.newServingsAmount)
            is GroceryListAction.Clear -> clearGroceryList()
            is GroceryListAction.OnRecipeClick -> handleRecipeClick(action.recipeId)
            is GroceryListAction.ToggleIngredient -> TODO()
            is GroceryListAction.CopyIngredients -> TODO()
            is GroceryListAction.OnCellAction -> handleCellAction(action)
        }
    }

    private fun changeServingsAmount(recipeId: String, newServingsAmount: Int) {
        viewModelScope.launch {
            groceryListRepository.updateServings(recipeId, newServingsAmount)
        }
    }

    private fun clearGroceryList() {
        viewModelScope.launch {
            groceryListRepository.clearAllGroceryList()
        }
    }

    private fun handleRecipeClick(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiEvent.emit(GroceryUiEvent.OpenRecipe(recipeId))
        }
    }

    private fun handleCellAction(action: GroceryListAction.OnCellAction) {
        viewModelScope.launch(Dispatchers.IO) {
            when (action.action) {
                CellAction.LocalPhoneClick -> snackbarHandler.showLocalPhoneMessage()
                CellAction.FavoriteClick -> handleFavoriteClick(action.recipeId, action.recipeTitle)
                CellAction.GroceryListClick -> groceryListClick(action.recipeId, action.recipeTitle)
            }
        }
    }

    private suspend fun handleFavoriteClick(recipeId: String, recipeTitle: String) {
        val isFavorite = favoriteRepository.updateFavorite(recipeId)
        if (isFavorite) {
            val result = snackbarHandler.showFavoriteMessage(recipeTitle)
            if (result == SnackbarResultUiModel.ActionPerformed) {
                favoriteRepository.updateFavorite(recipeId)
            }
        }
    }

    private suspend fun groceryListClick(recipeId: String, recipeTitle: String) {
        val isInGroceryList = groceryListRepository.toggleInGroceryList(recipeId)
        if (isInGroceryList) {
            val result = snackbarHandler.showGroceryListMessage(recipeTitle)
            if (result == SnackbarResultUiModel.ActionPerformed) {
                groceryListRepository.toggleInGroceryList(recipeId)
            }
        }
    }
}
