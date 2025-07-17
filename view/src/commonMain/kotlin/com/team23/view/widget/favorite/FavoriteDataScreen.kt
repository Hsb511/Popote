package com.team23.view.widget.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState

@Composable
fun FavoriteDataScreen(
	state: FavoriteUiState.Data.WithFavorites,
	onRecipeClick: (String) -> Unit,
	onFavoriteClick: (String) -> Unit,
	onDisplayClick: () -> Unit,
	onLocalPhoneClick: () -> Unit,
	modifier: Modifier = Modifier,
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
