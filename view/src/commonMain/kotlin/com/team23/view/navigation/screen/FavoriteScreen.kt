package com.team23.view.navigation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.neuracrsrecipes.viewmodel.FavoriteViewModel
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.favorite.FavoriteDataEmptyScreen
import com.team23.view.widget.favorite.FavoriteDataScreen
import org.koin.compose.koinInject

internal data class FavoriteScreen(
    val scrollState: ScrollState,
    val heightToBeFaded: MutableState<Float>,
    val title: MutableState<String?>,
) : Screen {

    @Composable
    override fun Content() {
        val favoriteViewModel = koinInject<FavoriteViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow
        val modifier = Modifier

        FavoriteScreen(
            favoriteUiState = favoriteViewModel.uiState.collectAsState().value,
            onRecipeClick = { recipe ->
                appNavigator.navigateToRecipe(navigator, recipe.id, scrollState, heightToBeFaded, title)
            },
            onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
            onDisplayClick = favoriteViewModel::onDisplayTypeClick,
            onLocalPhoneClick = favoriteViewModel::onLocalPhoneClick,
            modifier = modifier,
        )
    }
}

@Composable
fun FavoriteScreen(
    favoriteUiState: FavoriteUiState,
    onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
    onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
    onDisplayClick: () -> Unit,
    onLocalPhoneClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (favoriteUiState) {
        is FavoriteUiState.Data.WithFavorites -> FavoriteDataScreen(favoriteUiState, onRecipeClick,
            onFavoriteClick, onDisplayClick, onLocalPhoneClick, modifier)
        is FavoriteUiState.Data.Empty -> FavoriteDataEmptyScreen(modifier = modifier)
        is FavoriteUiState.Loading -> {}
    }
}
