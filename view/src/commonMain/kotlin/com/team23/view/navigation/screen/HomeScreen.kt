package com.team23.view.navigation.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.neuracrsrecipes.viewmodel.HomeViewModel
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.home.HomeContentData
import com.team23.view.widget.home.HomeContentLoading
import org.koin.compose.koinInject

internal data class HomeScreen(
    val scrollState: ScrollState,
    val heightToBeFaded: MutableState<Float>,
    val title: MutableState<String?>,
) : Screen {

    @Composable
    override fun Content() {
        val homeViewModel = koinInject<HomeViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow

        HomeScreen(
            homeUiState = homeViewModel.uiState.collectAsState(initial = HomeUiState.Loading).value,
            homeRecipeClick = { recipeId ->
                appNavigator.navigateToRecipe(navigator, recipeId, scrollState, heightToBeFaded, title)
            },
            onFavoriteClick = homeViewModel::favoriteClick,
            onLocalPhoneClick = homeViewModel::onLocalPhoneClick,
        )
    }
}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    homeRecipeClick: (String) -> Unit,
    onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
    onLocalPhoneClick: () -> Unit,
) {
    when (homeUiState) {
        is HomeUiState.Loading -> HomeContentLoading()
        is HomeUiState.Error -> ErrorScreen(homeUiState.errorUiModel)
        is HomeUiState.Data -> HomeContentData(
            homeUiState.recipes, homeRecipeClick, onFavoriteClick, onLocalPhoneClick,
        )
    }
}
