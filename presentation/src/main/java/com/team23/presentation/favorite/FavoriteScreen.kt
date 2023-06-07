package com.team23.presentation.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.common.samples.FavoriteSamples.SampleFavoriteStateProvider
import com.team23.presentation.favorite.models.FavoriteUiState
import com.team23.presentation.favorite.views.FavoriteDataEmptyScreen
import com.team23.presentation.favorite.views.FavoriteDataScreen
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@Composable
fun FavoriteScreen(
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
	favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
	val favoriteUiState by favoriteViewModel.uiState.collectAsState()
	FavoriteScreen(
		favoriteUiState = favoriteUiState,
		onRecipeClick = onRecipeClick,
		onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
		onDisplayClick = { favoriteViewModel.onDisplayTypeClick() },
		modifier = modifier,
	)
}

@Composable
fun FavoriteScreen(
	favoriteUiState: FavoriteUiState,
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onDisplayClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	when (favoriteUiState) {
		is FavoriteUiState.Data.WithFavorites -> FavoriteDataScreen(
			state = favoriteUiState,
			onRecipeClick = onRecipeClick,
			onFavoriteClick = onFavoriteClick,
			onDisplayClick = onDisplayClick,
			modifier = modifier,
		)

		is FavoriteUiState.Data.Empty -> FavoriteDataEmptyScreen(modifier = modifier)
		is FavoriteUiState.Loading -> {}
	}
}

@Composable
@Preview(showSystemUi = true)
fun FavoriteScreenPreview(@PreviewParameter(SampleFavoriteStateProvider::class) favoriteUiState: FavoriteUiState) {
	NeuracrTheme {
		FavoriteScreen(
			favoriteUiState = favoriteUiState,
			onRecipeClick = {},
			onFavoriteClick = {},
			onDisplayClick = {},
		)
	}
}
