package com.team23.presentation.favorite.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.team23.design_system.display.DisplayType
import com.team23.design_system.display.SampleDisplayTypeProvider
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.common.samples.RecipeSamples
import com.team23.presentation.favorite.models.FavoriteUiState
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteDataScreen(
	state: FavoriteUiState.Data.WithFavorites,
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onDisplayClick: () -> Unit,
	onLocalPhoneClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	val displayType = state.displayType
	val summarizedRecipes = state.favorites

	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Adaptive(if (displayType == DisplayType.SmallCard) 150.dp else 300.dp),
		contentPadding = PaddingValues(if (displayType == DisplayType.BigCard) 32.dp else 16.dp),
		verticalItemSpacing = 16.dp,
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier.fillMaxSize()
	) {
		item(span = StaggeredGridItemSpan.FullLine) {
			Spacer(modifier = Modifier.height(64.dp + 8.dp))
		}
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
				onRecipeClick = onRecipeClick,
				onFavoriteClick = onFavoriteClick,
				onLocalPhoneClick = onLocalPhoneClick,
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
fun FavoriteDataScreenPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
	NeuracrTheme {
		FavoriteDataScreen(
			state = FavoriteUiState.Data.WithFavorites(
				displayType = displayType,
				favorites = listOf(
					RecipeSamples.summarizedRecipeSample,
					RecipeSamples.summarizedRecipeSample,
					RecipeSamples.summarizedRecipeSample
				),
			),
			onRecipeClick = {},
			onFavoriteClick = {},
			onDisplayClick = {},
			onLocalPhoneClick = {},
		)
	}
}
