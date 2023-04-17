package com.example.presentation.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.presentation.recipe.models.RecipeUiState
import com.example.presentation.recipe.views.RecipeContentData

@Composable
fun RecipeScreen(
	cleanRecipeId: String?,
	modifier: Modifier = Modifier,
	recipeViewModel: RecipeViewModel = hiltViewModel()
) {
	recipeViewModel.getRecipe(cleanRecipeId)
	RecipeScreen(
		recipeUiState = recipeViewModel.uiState.collectAsState().value,
		modifier = modifier
	)
}

@Composable
fun RecipeScreen(recipeUiState: RecipeUiState, modifier: Modifier) {
	when (recipeUiState) {
		is RecipeUiState.Data -> RecipeContentData(recipeUiState.recipe, modifier)
		is RecipeUiState.Error -> Text(text = recipeUiState.message, modifier)
		is RecipeUiState.Loading -> Text(text = "TODO loading...", modifier)
	}
}
