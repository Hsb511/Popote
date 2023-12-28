package com.team23.neuracrsrecipes.model.uistate

import com.team23.neuracrsrecipes.model.uimodel.ErrorPageUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel

sealed class RecipeUiState {
	data object Loading : RecipeUiState()
	data class Error(val errorPageUiModel: ErrorPageUiModel) : RecipeUiState()
	data class Data(val recipe: RecipeUiModel) : RecipeUiState()
}
