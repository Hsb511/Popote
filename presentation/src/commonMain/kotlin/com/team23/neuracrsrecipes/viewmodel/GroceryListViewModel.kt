package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.neuracrsrecipes.mapper.GroceryListUiMapper
import com.team23.neuracrsrecipes.model.action.GroceryListAction
import com.team23.neuracrsrecipes.model.uimodel.GroceryListUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class GroceryListViewModel(
    groceryListUiMapper: GroceryListUiMapper,
    val viewModelScope: CoroutineScope,
    val groceryListRepository: GroceryListRepository,
) {
    val uiState: StateFlow<GroceryListUiModel> = groceryListRepository.getGroceryList()
        .map(groceryListUiMapper::toUiModel)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = GroceryListUiModel()
        )

    fun onAction(action: GroceryListAction) {
        when (action) {
            is GroceryListAction.ChangeServingsAmount -> TODO()
            is GroceryListAction.Clear -> clearGroceryList()
            is GroceryListAction.OnRecipeClick -> TODO()
            is GroceryListAction.ToggleIngredient -> TODO()
            is GroceryListAction.CopyIngredients -> TODO()
        }
    }

    private fun clearGroceryList() {
        viewModelScope.launch {
            groceryListRepository.clearAllGroceryList()
        }
    }
}
