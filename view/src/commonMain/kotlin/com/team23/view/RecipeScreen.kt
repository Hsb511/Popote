package com.team23.view

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.RecipeUiState
import com.team23.view.widget.recipe.RecipeContentData
import com.team23.view.widget.recipe.RecipeContentLoading


/*
@Composable
fun RecipeScreen(
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	title: MutableState<String?>,
	snackbarHostState: SnackbarHostState,
	cleanRecipeId: String?,
	onTagClicked: (String) -> Unit,
	navigationHandler: NavigationHandler,
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
		onUpdateLocalRecipe = { recipeViewModel.onUpdateLocalRecipe(navigationHandler) },
		onDeleteLocalRecipe = { recipeViewModel.onDeleteLocalRecipe(snackbarHostState, context, navigationHandler) },
		modifier = modifier,
	)
}
 */

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
    onUpdateLocalRecipe: () -> Unit,
    onDeleteLocalRecipe: () -> Unit,
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
                onUpdateLocalRecipe = onUpdateLocalRecipe,
                onDeleteLocalRecipe = onDeleteLocalRecipe,
                modifier = modifier,
            )
            title.value = recipeUiState.recipe.title
        }

        is RecipeUiState.Error -> ErrorScreen(recipeUiState.errorUiModel, modifier)
        is RecipeUiState.Loading -> RecipeContentLoading(modifier)
    }
}
