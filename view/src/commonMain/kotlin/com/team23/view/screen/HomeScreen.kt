package com.team23.view.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.neuracrsrecipes.viewmodel.HomeViewModel
import com.team23.view.widget.home.HomeContentData
import com.team23.view.widget.home.HomeContentLoading
import org.koin.compose.koinInject

@Composable
internal fun HomeScreen(homeRecipeClick: (String) -> Unit) {
	val homeViewModel = koinInject<HomeViewModel>()

	when (val homeUiState = homeViewModel.uiState.collectAsState(initial = HomeUiState.Loading).value) {
		is HomeUiState.Loading -> HomeContentLoading()
		is HomeUiState.Error -> ErrorScreen(homeUiState.errorUiModel)
		is HomeUiState.Data -> HomeContentData(
			summarizedRecipeUiModels = homeUiState.recipes,
			homeRecipeClick = homeRecipeClick,
			onFavoriteClick = homeViewModel::favoriteClick,
			onLocalPhoneClick = homeViewModel::onLocalPhoneClick,
		)
	}
}
