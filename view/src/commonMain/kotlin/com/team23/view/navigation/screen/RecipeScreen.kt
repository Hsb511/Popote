package com.team23.view.navigation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.team23.neuracrsrecipes.model.uistate.RecipeUiState
import com.team23.neuracrsrecipes.viewmodel.RecipeViewModel
import com.team23.view.widget.recipe.RecipeContentData
import com.team23.view.widget.recipe.RecipeContentLoading
import org.koin.compose.koinInject

internal data class RecipeScreen(val cleanRecipeId: String?): Screen {

    @Composable
    override fun Content() {
        RecipeScreen(
            cleanRecipeId = cleanRecipeId,
            scrollState = rememberScrollState(),
            heightToBeFaded = remember { mutableStateOf(0.0f) },
            title = remember { mutableStateOf("test") },
            onTagClicked = {},
        )
    }
}

@Composable
fun RecipeScreen(
    cleanRecipeId: String?,
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    title: MutableState<String?>,
    onTagClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
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
