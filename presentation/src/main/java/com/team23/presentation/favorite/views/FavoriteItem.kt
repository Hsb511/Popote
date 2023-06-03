package com.team23.presentation.favorite.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.common.samples.RecipeSamples.SampleDisplayTypeProvider
import com.team23.presentation.common.samples.RecipeSamples.summarizedRecipeSample
import com.team23.presentation.favorite.models.DisplayType
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.home.views.HomeRecipeCard
import com.team23.presentation.search.views.SearchRecipeCard

@Composable
fun FavoriteItem(
	displayType: DisplayType,
	summarizedRecipe: SummarizedRecipeUiModel,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier
) {
	when (displayType) {
		DisplayType.BigCard,
		DisplayType.SmallCard -> HomeRecipeCard(
			summarizedRecipeUiModel = summarizedRecipe,
			onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
			modifier = modifier,
		)

		DisplayType.List -> SearchRecipeCard(
			summarizedRecipeUiModel = summarizedRecipe,
			onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
			modifier = modifier,
		)
	}
}

@Composable
@Preview(showBackground = true)
fun FavoriteItemPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
	NeuracrTheme {
		FavoriteItem(
			displayType = displayType,
			summarizedRecipe = summarizedRecipeSample,
			onFavoriteClick = {}
		)
	}
}
