package com.example.presentation.recipe.models

sealed class RecipeUiState {
	object Loading : RecipeUiState()
	object Error : RecipeUiState()
	data class Data(val recipe: RecipeUiModel) : RecipeUiState()
}
