package com.team23.presentation.favorite.views

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.design_system.cell.NeuracrCell
import com.team23.design_system.display.DisplayType
import com.team23.design_system.display.SampleDisplayTypeProvider
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.common.samples.RecipeSamples.summarizedRecipeSample
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.home.models.toNeuracrCellProperty

@Composable
fun FavoriteItem(
	displayType: DisplayType,
	summarizedRecipe: SummarizedRecipeUiModel,
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier
) {
	NeuracrCell(
		neuracrCellProperty = summarizedRecipe.toNeuracrCellProperty(
			displayType = displayType,
			onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
		),
		modifier = modifier.clickable {
			onRecipeClick(summarizedRecipe)
		}
	)
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
