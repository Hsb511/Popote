package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.neuracrsrecipes.viewmodel.HomeViewModel
import com.team23.view.widget.home.HomeContentData
import com.team23.view.widget.home.HomeContentLoading
import org.koin.compose.koinInject

internal object HomeScreen: Screen {

	@Composable
	override fun Content() {
		val homeViewModel = koinInject<HomeViewModel>()
		val navigator = LocalNavigator.currentOrThrow

		when (val homeUiState = homeViewModel.uiState.collectAsState(initial = HomeUiState.Loading).value) {
			is HomeUiState.Loading -> HomeContentLoading()
			is HomeUiState.Error -> ErrorScreen(homeUiState.errorUiModel)
			is HomeUiState.Data -> HomeContentData(
				summarizedRecipeUiModels = homeUiState.recipes,
				homeRecipeClick = { recipeId -> navigator.push(RecipeScreen(recipeId)) },
				onFavoriteClick = homeViewModel::favoriteClick,
				onLocalPhoneClick = homeViewModel::onLocalPhoneClick,
			)
		}
	}
}
