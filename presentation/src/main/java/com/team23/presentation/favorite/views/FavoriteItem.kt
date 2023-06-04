package com.team23.presentation.favorite.views

import androidx.compose.foundation.clickable
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
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier
) {
	val recipeItemModifier = modifier.clickable {
		onRecipeClick(summarizedRecipe)
	}
	when (displayType) {
		DisplayType.BigCard,
		DisplayType.SmallCard -> HomeRecipeCard(
			summarizedRecipeUiModel = summarizedRecipe,
			onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
			modifier = recipeItemModifier,
			isFavoritePage = true,
		)

		DisplayType.List -> SearchRecipeCard(
			summarizedRecipeUiModel = summarizedRecipe,
			onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
			modifier = recipeItemModifier,
			isFavoritePage = true,
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
			onFavoriteClick = {},
			onRecipeClick = {},
		)
	}
}
