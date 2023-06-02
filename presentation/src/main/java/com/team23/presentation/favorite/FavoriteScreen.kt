package com.team23.presentation.favorite

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.display.DisplayBigCard
import com.team23.design_system.display.DisplayList
import com.team23.design_system.display.DisplaySmallCard
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.common.extensions.next
import com.team23.presentation.favorite.models.DisplayType
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.home.views.HomeRecipeCard
import com.team23.presentation.search.views.SearchRecipeCard

@Composable
fun FavoriteScreen(
	modifier: Modifier = Modifier,
	favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {
	var displayType by remember { mutableStateOf(DisplayType.BigCard) }
	val summarizedRecipes by favoriteViewModel.favorites.collectAsState(initial = emptyList())
	FavoriteScreen(
		summarizedRecipes = summarizedRecipes,
		displayType = displayType,
		onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
		onDisplayClick = { displayType = displayType.next() },
		modifier = modifier,
	)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
	summarizedRecipes: List<SummarizedRecipeUiModel>,
	displayType: DisplayType,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	onDisplayClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val animation = tween<Dp>(
		durationMillis = 600,
		easing = FastOutSlowInEasing
	)
	val contentPadding: Dp by animateDpAsState(if (displayType == DisplayType.BigCard) 32.dp else 16.dp, animation)
	val headerPadding: Dp by animateDpAsState(if (displayType == DisplayType.BigCard) 0.dp else 16.dp, animation)

	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Adaptive(if (displayType == DisplayType.SmallCard) 150.dp else 300.dp),
		contentPadding = PaddingValues(contentPadding),
		verticalItemSpacing = 16.dp,
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier.fillMaxSize()
	) {
		item(span = StaggeredGridItemSpan.FullLine) {
			Row(
				modifier = Modifier
					.fillMaxSize()
					.padding(top = headerPadding, start = headerPadding, end = headerPadding)
			) {
				Text(
					text = stringResource(id = R.string.favorite_title),
					style = MaterialTheme.typography.displaySmall,
					color = MaterialTheme.colorScheme.onBackground,
				)
				Spacer(modifier = Modifier.weight(1f))
				IconButton(
					onClick = onDisplayClick,
					modifier = Modifier.offset(x = 4.dp, y = (-4).dp)
				) {
					val tint = MaterialTheme.colorScheme.onBackground
					val iconModifier = Modifier.padding(all = 6.dp)
					when (displayType.next()) {
						DisplayType.BigCard -> DisplayBigCard(tint = tint, modifier = iconModifier)
						DisplayType.SmallCard -> DisplaySmallCard(tint = tint, modifier = iconModifier)
						DisplayType.List -> DisplayList(tint = tint, modifier = iconModifier)
					}
				}
			}
		}
		items(summarizedRecipes) { summarizedRecipe ->
			when (displayType) {
				DisplayType.BigCard,
				DisplayType.SmallCard -> HomeRecipeCard(
					summarizedRecipeUiModel = summarizedRecipe,
					onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
				)

				DisplayType.List -> SearchRecipeCard(
					summarizedRecipeUiModel = summarizedRecipe,
					onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
				)
			}
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun FavoriteScreenPreview() {
	NeuracrTheme {
		FavoriteScreen()
	}
}
