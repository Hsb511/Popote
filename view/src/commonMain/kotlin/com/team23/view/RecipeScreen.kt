package com.team23.view

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.RecipeUiState
import com.team23.neuracrsrecipes.viewmodel.RecipeViewModel
import com.team23.view.widget.recipe.RecipeContentData
import com.team23.view.widget.recipe.RecipeContentLoading
import org.koin.compose.koinInject

@Composable
fun RecipeScreen(
    cleanRecipeId: String?,
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    title: MutableState<String?>,
    onTagClicked: (String) -> Unit,
    modifier: Modifier
) {
    val recipeViewModel = koinInject<RecipeViewModel>()
    recipeViewModel.getRecipe(cleanRecipeId)

    title.value = null
    when (val recipeUiState = recipeViewModel.uiState.collectAsState().value) {
        is RecipeUiState.Data -> {
            RecipeContentData(
                recipeUiModel = recipeUiState.recipe,
                scrollState = scrollState,
                heightToBeFaded = heightToBeFaded,
                currentServingsAmount = recipeViewModel.currentServingsAmount.value.toString(),
                onValueChanged = recipeViewModel::updateRecipeData,
                onAddOneServing = recipeViewModel::addOneService,
                onSubtractOneServing = recipeViewModel::subtractOneService,
                onTagClicked = onTagClicked,
                onFavoriteClick = recipeViewModel::favoriteClick,
                onLocalPhoneClick = recipeViewModel::onLocalPhoneClick,
                onUpdateLocalRecipe = recipeViewModel::onUpdateLocalRecipe,
                onDeleteLocalRecipe = recipeViewModel::onDeleteLocalRecipe,
                modifier = modifier,
            )
            title.value = recipeUiState.recipe.title
        }

        is RecipeUiState.Error -> ErrorScreen(recipeUiState.errorUiModel, modifier)
        is RecipeUiState.Loading -> RecipeContentLoading(modifier)
    }
}
