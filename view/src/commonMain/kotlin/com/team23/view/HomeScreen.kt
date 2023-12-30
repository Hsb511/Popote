package com.team23.view

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.view.widget.home.HomeContentData
import com.team23.view.widget.home.HomeContentLoading

/*
@Composable
fun HomeScreen(
	snackbarHostState: SnackbarHostState,
	onRecipeClick: (String) -> Unit,
	homeViewModel: HomeViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	homeViewModel.snackbarHandler = SnackbarHandler(snackbarHostState, context)
	val state = homeViewModel.uiState.collectAsState(initial = HomeUiState.Loading)

	HomeScreen(
		homeUiState = state.value,
		homeRecipeClick = onRecipeClick,
		onFavoriteClick = { recipe -> homeViewModel.favoriteClick(recipe) },
		onLocalPhoneClick = { homeViewModel.onLocalPhoneClick() },
	)
}
*/

@Composable
internal fun HomeScreen(
	homeUiState: HomeUiState,
	homeRecipeClick: (String) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onLocalPhoneClick: () -> Unit,
) {
	when (homeUiState) {
		is HomeUiState.Loading -> HomeContentLoading()
		is HomeUiState.Error -> ErrorScreen(homeUiState.errorUiModel)
		is HomeUiState.Data -> HomeContentData(
			summarizedRecipeUiModels = homeUiState.recipes,
			homeRecipeClick = homeRecipeClick,
			onFavoriteClick = onFavoriteClick,
			onLocalPhoneClick = onLocalPhoneClick,
		)
	}
}
