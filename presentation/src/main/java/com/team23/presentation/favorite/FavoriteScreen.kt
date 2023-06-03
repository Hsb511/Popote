package com.team23.presentation.favorite

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.favorite.models.DisplayType
import com.team23.presentation.favorite.models.FavoriteUiState
import com.team23.presentation.favorite.views.FavoriteHeader
import com.team23.presentation.favorite.views.FavoriteItem
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@Composable
fun FavoriteScreen(
	modifier: Modifier = Modifier,
	favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
	val favoriteUiState by favoriteViewModel.uiState.collectAsState()
	FavoriteScreen(
		favoriteUiState = favoriteUiState,
		onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
		onDisplayClick = { favoriteViewModel.onDisplayTypeClick() },
		modifier = modifier,
	)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
	favoriteUiState: FavoriteUiState,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onDisplayClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	when (favoriteUiState) {
		is FavoriteUiState.Data -> {
			val displayType = favoriteUiState.displayType
			val summarizedRecipes = favoriteUiState.favorites
			val animation = tween<Dp>(durationMillis = 600, easing = FastOutSlowInEasing)
			val contentPadding: Dp by animateDpAsState(
				if (displayType == DisplayType.BigCard) 32.dp else 16.dp,
				animation
			)

			LazyVerticalStaggeredGrid(
				columns = StaggeredGridCells.Adaptive(if (displayType == DisplayType.SmallCard) 150.dp else 300.dp),
				contentPadding = PaddingValues(contentPadding),
				verticalItemSpacing = 16.dp,
				horizontalArrangement = Arrangement.spacedBy(16.dp),
				modifier = modifier.fillMaxSize()
			) {
				item(span = StaggeredGridItemSpan.FullLine) {
					FavoriteHeader(
						displayType = displayType,
						onDisplayClick = onDisplayClick,
					)
				}
				items(summarizedRecipes) { summarizedRecipe ->
					FavoriteItem(
						displayType = displayType,
						summarizedRecipe = summarizedRecipe,
						onFavoriteClick = onFavoriteClick,
					)
				}
			}
		}

		is FavoriteUiState.Loading -> {}
	}
}

@Composable
@Preview(showSystemUi = true)
fun FavoriteScreenPreview() {
	NeuracrTheme {
		FavoriteScreen()
	}
}
