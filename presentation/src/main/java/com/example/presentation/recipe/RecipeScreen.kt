package com.example.presentation.recipe

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
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
		currentServingsAmount = recipeViewModel.currentServingsAmount.value.toString(),
		onValueChanged = { currentServingsAmount -> recipeViewModel.updateRecipeData(currentServingsAmount) },
		onAddOneServing = { recipeViewModel.addOneService() },
		onSubtractOneServing = { recipeViewModel.subtractOneService() },
		modifier = modifier,
	)
}

@Composable
fun RecipeScreen(
	recipeUiState: RecipeUiState,
	currentServingsAmount: String,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	modifier: Modifier
) {
	when (recipeUiState) {
		is RecipeUiState.Data -> RecipeContentData(
			recipeUiModel = recipeUiState.recipe,
			currentServingsAmount = currentServingsAmount,
			onValueChanged = onValueChanged,
			onAddOneServing = onAddOneServing,
			onSubtractOneServing = onSubtractOneServing,
			modifier = modifier
		)
		is RecipeUiState.Error -> Text(text = recipeUiState.message, modifier)
		is RecipeUiState.Loading -> Text(text = "TODO loading...", modifier)
	}
}