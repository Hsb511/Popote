package com.team23.neuracrsrecipes.viewmodel

import com.team23.domain.grocery.usecase.GetWeeklyGroceryListUseCase
import com.team23.neuracrsrecipes.mapper.WeeklyGroceryListUiMapper
import com.team23.neuracrsrecipes.model.action.WeeklyGroceryListAction
import com.team23.neuracrsrecipes.model.uimodel.WeeklyGroceryListUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class WeeklyGroceryListViewModel(
    getWeeklyGroceryListUseCase: GetWeeklyGroceryListUseCase,
    weeklyGroceryListUiMapper: WeeklyGroceryListUiMapper,
    viewModelScope: CoroutineScope,
) {
    val uiState: StateFlow<WeeklyGroceryListUiModel> = getWeeklyGroceryListUseCase
        .invoke()
        .map(weeklyGroceryListUiMapper::toUiModel)
        .stateIn(
            scope = viewModelScope,
            started = kotlinx.coroutines.flow.SharingStarted.Lazily,
            initialValue = WeeklyGroceryListUiModel()
        )

    fun onAction(action: WeeklyGroceryListAction) {

    }
}
