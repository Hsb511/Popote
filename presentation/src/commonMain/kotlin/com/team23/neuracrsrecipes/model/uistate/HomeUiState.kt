package com.team23.neuracrsrecipes.model.uistate

import com.team23.neuracrsrecipes.model.uimodel.ErrorPageUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

sealed class HomeUiState {
    data object Loading : HomeUiState()
    data class Error(val errorPageUiModel: ErrorPageUiModel) : HomeUiState()
    data class Data(val recipes: List<SummarizedRecipeUiModel>) : HomeUiState()
}
