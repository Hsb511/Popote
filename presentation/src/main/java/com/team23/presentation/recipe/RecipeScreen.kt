package com.team23.presentation.recipe

import androidx.compose.foundation.ScrollState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.error.NeuracrError
import com.team23.presentation.recipe.models.RecipeUiModel
import com.team23.presentation.recipe.models.RecipeUiState
import com.team23.presentation.recipe.views.RecipeContentData
import com.team23.presentation.recipe.views.RecipeContentLoading

@Composable
fun RecipeScreen(
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	title: MutableState<String?>,
	snackbarHostState: SnackbarHostState,
	cleanRecipeId: String?,
	onTagClicked: (String) -> Unit,
	modifier: Modifier = Modifier,
	recipeViewModel: RecipeViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	recipeViewModel.getRecipe(cleanRecipeId)
	RecipeScreen(
		recipeUiState = recipeViewModel.uiState.collectAsState().value,
		scrollState = scrollState,
		heightToBeFaded = heightToBeFaded,
		title = title,
		currentServingsAmount = recipeViewModel.currentServingsAmount.value.toString(),
		onValueChanged = { currentServingsAmount -> recipeViewModel.updateRecipeData(currentServingsAmount) },
		onAddOneServing = { recipeViewModel.addOneService() },
		onSubtractOneServing = { recipeViewModel.subtractOneService() },
		onTagClicked = onTagClicked,
		onFavoriteClick = { recipe -> recipeViewModel.favoriteClick(recipe, snackbarHostState, context) },
		onLocalPhoneClick = { recipeViewModel.onLocalPhoneClick(snackbarHostState, context) },
		modifier = modifier,
	)
}

@Composable
fun RecipeScreen(
	recipeUiState: RecipeUiState,
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	title: MutableState<String?>,
	currentServingsAmount: String,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	onTagClicked: (String) -> Unit,
	onFavoriteClick: (RecipeUiModel) -> Unit,
	onLocalPhoneClick: () -> Unit,
	modifier: Modifier
) {
	title.value = null
	when (recipeUiState) {
		is RecipeUiState.Data -> {
			RecipeContentData(
				recipeUiModel = recipeUiState.recipe,
				scrollState = scrollState,
				heightToBeFaded = heightToBeFaded,
				currentServingsAmount = currentServingsAmount,
				onValueChanged = onValueChanged,
				onAddOneServing = onAddOneServing,
				onSubtractOneServing = onSubtractOneServing,
				onTagClicked = onTagClicked,
				onFavoriteClick = onFavoriteClick,
				onLocalPhoneClick = onLocalPhoneClick,
				modifier = modifier,
			)
			title.value = recipeUiState.recipe.title
		}
		is RecipeUiState.Error -> NeuracrError(recipeUiState.message, modifier)
		is RecipeUiState.Loading -> RecipeContentLoading(modifier)
	}
}
