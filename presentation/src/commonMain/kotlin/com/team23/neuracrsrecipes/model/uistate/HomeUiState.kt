package com.team23.neuracrsrecipes.model.uistate

import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Error(val errorUiModel: ErrorUiModel) : HomeUiState()
    data class Data(
        val recipes: List<SummarizedRecipeUiModel>,
        val isRefreshing: Boolean = false,
    ) : HomeUiState()
}
