package com.team23.neuracrsrecipes.model.uistate

import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel

sealed class RecipeUiState {
	data object Loading : RecipeUiState()
	data class Error(val errorUiModel: ErrorUiModel) : RecipeUiState()
	data class Data(val recipe: RecipeUiModel) : RecipeUiState()
}
