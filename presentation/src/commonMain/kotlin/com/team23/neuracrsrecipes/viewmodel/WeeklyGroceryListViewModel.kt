package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.neuracrsrecipes.mapper.WeeklyGroceryListUiMapper
import com.team23.neuracrsrecipes.model.action.WeeklyGroceryListAction
import com.team23.neuracrsrecipes.model.uimodel.WeeklyGroceryListUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeeklyGroceryListViewModel(
    weeklyGroceryListUiMapper: WeeklyGroceryListUiMapper,
    val viewModelScope: CoroutineScope,
    val groceryListRepository: GroceryListRepository,
) {
    val uiState: StateFlow<WeeklyGroceryListUiModel> = groceryListRepository.getGroceryList()
        .map(weeklyGroceryListUiMapper::toUiModel)
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Lazily,
            initialValue = WeeklyGroceryListUiModel()
        )

    fun onAction(action: WeeklyGroceryListAction) {
        when (action) {
            is WeeklyGroceryListAction.ChangeServingsAmount -> TODO()
            is WeeklyGroceryListAction.Clear -> clearGroceryList()
            is WeeklyGroceryListAction.OnRecipeClick -> TODO()
            is WeeklyGroceryListAction.ToggleIngredient -> TODO()
        }
    }

    private fun clearGroceryList() {
        viewModelScope.launch {
            groceryListRepository.clearAllGroceryList()
        }
    }
}
