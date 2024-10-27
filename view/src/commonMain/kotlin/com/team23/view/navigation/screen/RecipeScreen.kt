package com.team23.view.navigation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uistate.RecipeUiState
import com.team23.neuracrsrecipes.viewmodel.RecipeViewModel
import com.team23.view.LocalHeightToBeFaded
import com.team23.view.LocalScrollState
import com.team23.view.LocalTitle
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.recipe.RecipeContentData
import com.team23.view.widget.recipe.RecipeContentLoading
import org.koin.compose.koinInject

internal data class RecipeScreen(val cleanRecipeId: String) : Screen {

    @Composable
    override fun Content() {
        val scrollState: ScrollState = LocalScrollState.current
        val heightToBeFaded: MutableState<Float> = LocalHeightToBeFaded.current
        val title: MutableState<String?> = LocalTitle.current

        RecipeComposeScreen(
            cleanRecipeId = cleanRecipeId,
            scrollState = scrollState,
            heightToBeFaded = heightToBeFaded,
            title = title,
        )
    }
}

@Composable
private fun RecipeComposeScreen(
    cleanRecipeId: String,
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    title: MutableState<String?>,
    modifier: Modifier = Modifier,
) {
    val recipeViewModel = koinInject<RecipeViewModel>()
    val appNavigator = koinInject<AppNavigator>()
    val navigator = LocalNavigator.currentOrThrow

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
                onTagClicked = { tagId ->
                    appNavigator.navigateToSearch(navigator, tagId)
                },
                onFavoriteClick = recipeViewModel::favoriteClick,
                onLocalPhoneClick = recipeViewModel::onLocalPhoneClick,
                onUpdateLocalRecipe = {
                    recipeViewModel.onUpdateLocalRecipe {
                        appNavigator.navigateToAdd(navigator)
                    }
                },
                onDeleteLocalRecipe = {
                    recipeViewModel.onDeleteLocalRecipe {
                        appNavigator.navigateToHome(navigator)
                    }
                },
                modifier = modifier,
            )
            title.value = recipeUiState.recipe.title
        }

        is RecipeUiState.Error -> ErrorScreen(recipeUiState.errorUiModel, modifier)
        is RecipeUiState.Loading -> RecipeContentLoading(modifier)
    }
}
