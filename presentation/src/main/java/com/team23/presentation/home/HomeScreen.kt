package com.team23.presentation.home

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.R
import com.team23.design_system.error.NeuracrError
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.common.handlers.SnackbarHandler
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
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
	homeViewModel: HomeViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	homeViewModel.snackbarHandler = SnackbarHandler(snackbarHostState, context)
	val state = homeViewModel.uiState.collectAsState(initial = Loading)

	HomeScreen(
		homeUiState = state.value,
		homeRecipeClick = onRecipeClick,
		onFavoriteClick = { recipe -> homeViewModel.favoriteClick(recipe) },
		modifier = modifier
	)
}

@Composable
private fun HomeScreen(
	homeUiState: HomeUiState,
	homeRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier
) {
	when (homeUiState) {
		is Loading -> HomeContentLoading(modifier)
		is Error -> NeuracrError(homeUiState.message, modifier)
		is Data -> HomeContentData(
			summarizedRecipeUiModels = homeUiState.recipes,
			homeRecipeClick = homeRecipeClick,
			onFavoriteClick = onFavoriteClick,
			modifier = modifier,
		)
	}
}

@Composable
@Preview(showSystemUi = true)
private fun HomeScreenPreview() {
	NeuracrTheme {
		HomeScreen(
			homeUiState = Data(
				recipes = List(6) {
					SummarizedRecipeUiModel(
						id = "",
						title = "bretzels",
						imageProperty = NeuracrImageProperty.Resource(
							contentDescription = null,
							imageRes = R.drawable.bretzel
						),
						flagProperty = NeuracrFlagProperty.FRENCH,
						isFavorite = true,
					)
				}
			), {}, {}
		)
	}
}
