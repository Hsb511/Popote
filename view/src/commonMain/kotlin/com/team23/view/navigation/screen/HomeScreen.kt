package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.action.HomeAction
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.neuracrsrecipes.viewmodel.HomeViewModel
import com.team23.view.LocalTitle
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.home.HomeContentData
import com.team23.view.widget.home.HomeContentLoading
import org.koin.compose.koinInject

internal data object HomeScreen: Screen {

    @Composable
    override fun Content() {
        val homeViewModel = koinInject<HomeViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow

        LocalTitle.current.value = null

        HomeScreen(
            homeUiState = homeViewModel.uiState.collectAsState(initial = HomeUiState.Loading).value,
            homeRecipeClick = { recipeId ->
                appNavigator.navigateToRecipe(navigator, recipeId)
            },
            onFavoriteClick = homeViewModel::favoriteClick,
            onLocalPhoneClick = homeViewModel::onLocalPhoneClick,
            onAction = homeViewModel::onAction,
        )
    }
}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    homeRecipeClick: (String) -> Unit,
    onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
    onLocalPhoneClick: () -> Unit,
    onAction: (HomeAction) -> Unit = {},
) {
    when (homeUiState) {
        is HomeUiState.Loading -> HomeContentLoading()
        is HomeUiState.Error -> ErrorScreen(homeUiState.errorUiModel)
        is HomeUiState.Data -> HomeContentData(
            homeUiState, homeRecipeClick, onFavoriteClick, onLocalPhoneClick, onAction,
        )
    }
}
