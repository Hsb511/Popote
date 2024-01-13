package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.neuracrsrecipes.viewmodel.FavoriteViewModel
import com.team23.view.widget.favorite.FavoriteDataEmptyScreen
import com.team23.view.widget.favorite.FavoriteDataScreen
import org.koin.compose.koinInject

@Composable
fun FavoriteScreen(
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
) {
	val favoriteViewModel = koinInject<FavoriteViewModel>()

	when (val favoriteUiState = favoriteViewModel.uiState.collectAsState().value) {
		is FavoriteUiState.Data.WithFavorites -> FavoriteDataScreen(
			state = favoriteUiState,
			onRecipeClick = onRecipeClick,
			onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
			onDisplayClick = favoriteViewModel::onDisplayTypeClick,
			onLocalPhoneClick = favoriteViewModel::onLocalPhoneClick,
			modifier = modifier,
		)

		is FavoriteUiState.Data.Empty -> FavoriteDataEmptyScreen(modifier = modifier)
		is FavoriteUiState.Loading -> {}
	}
}
