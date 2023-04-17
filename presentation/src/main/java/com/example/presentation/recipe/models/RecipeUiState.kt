package com.example.presentation.recipe.models

sealed class RecipeUiState {
	object Loading : RecipeUiState()
	data class Error(val message: String) : RecipeUiState()
	data class Data(val recipe: RecipeUiModel) : RecipeUiState()
}
