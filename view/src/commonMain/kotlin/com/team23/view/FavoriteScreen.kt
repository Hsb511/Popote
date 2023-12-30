package com.team23.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.view.widget.favorite.FavoriteDataEmptyScreen
import com.team23.view.widget.favorite.FavoriteDataScreen

/*@Composable
fun FavoriteScreen(
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	snackbarHostState: SnackbarHostState,
	modifier: Modifier = Modifier,
	favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
	val favoriteUiState by favoriteViewModel.uiState.collectAsState()
	val context = LocalContext.current
	FavoriteScreen(
		favoriteUiState = favoriteUiState,
		onRecipeClick = onRecipeClick,
		onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
		onDisplayClick = { favoriteViewModel.onDisplayTypeClick() },
		onLocalPhoneClick = { favoriteViewModel.onLocalPhoneClick(snackbarHostState, context) },
		modifier = modifier,
	)
}
*/

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
		is FavoriteUiState.Data.WithFavorites -> FavoriteDataScreen(
			state = favoriteUiState,
			onRecipeClick = onRecipeClick,
			onFavoriteClick = onFavoriteClick,
			onDisplayClick = onDisplayClick,
			onLocalPhoneClick = onLocalPhoneClick,
			modifier = modifier,
		)

		is FavoriteUiState.Data.Empty -> FavoriteDataEmptyScreen(modifier = modifier)
		is FavoriteUiState.Loading -> {}
	}
}
