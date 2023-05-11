package com.team23.presentation.recipe

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.error.NeuracrError
import com.team23.presentation.recipe.models.RecipeUiState
import com.team23.presentation.recipe.views.RecipeContentData
import com.team23.presentation.recipe.views.RecipeContentLoading

@Composable
fun RecipeScreen(
	cleanRecipeId: String?,
	onTagClicked: (String) -> Unit,
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
		onTagClicked = onTagClicked,
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
	onTagClicked: (String) -> Unit,
	modifier: Modifier
) {
	when (recipeUiState) {
		is RecipeUiState.Data -> RecipeContentData(
			recipeUiModel = recipeUiState.recipe,
			currentServingsAmount = currentServingsAmount,
			onValueChanged = onValueChanged,
			onAddOneServing = onAddOneServing,
			onSubtractOneServing = onSubtractOneServing,
			onTagClicked = onTagClicked,
			modifier = modifier
		)
		is RecipeUiState.Error -> NeuracrError(recipeUiState.message, modifier)
		is RecipeUiState.Loading -> RecipeContentLoading(modifier)
	}
}
