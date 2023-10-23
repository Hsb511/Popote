package com.team23.presentation.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.error.NeuracrError
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.common.handlers.SnackbarHandler
import com.team23.presentation.common.samples.RecipeSamples.summarizedRecipeSample
import com.team23.presentation.home.models.HomeUiState
import com.team23.presentation.home.models.HomeUiState.Data
import com.team23.presentation.home.models.HomeUiState.Error
import com.team23.presentation.home.models.HomeUiState.Loading
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.home.views.HomeContentData
import com.team23.presentation.home.views.HomeContentLoading

@Composable
fun HomeScreen(
	snackbarHostState: SnackbarHostState,
	onRecipeClick: (String) -> Unit,
	homeViewModel: HomeViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	homeViewModel.snackbarHandler = SnackbarHandler(snackbarHostState, context)
	val state = homeViewModel.uiState.collectAsState(initial = Loading)

	HomeScreen(
		homeUiState = state.value,
		homeRecipeClick = onRecipeClick,
		onFavoriteClick = { recipe -> homeViewModel.favoriteClick(recipe) },
		onLocalPhoneClick = { homeViewModel.onLocalPhoneClick() },
	)
}

@Composable
private fun HomeScreen(
	homeUiState: HomeUiState,
	homeRecipeClick: (String) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onLocalPhoneClick: () -> Unit,
) {
	when (homeUiState) {
		is Loading -> HomeContentLoading()
		is Error -> NeuracrError(homeUiState.message)
		is Data -> HomeContentData(
			summarizedRecipeUiModels = homeUiState.recipes,
			homeRecipeClick = homeRecipeClick,
			onFavoriteClick = onFavoriteClick,
			onLocalPhoneClick = onLocalPhoneClick,
		)
	}
}

@Composable
@Preview(showSystemUi = true)
private fun HomeScreenPreview() {
	NeuracrTheme {
		HomeScreen(
			homeUiState = Data(
				recipes = List(6) { summarizedRecipeSample }
			), {}, {}, {}
		)
	}
}
