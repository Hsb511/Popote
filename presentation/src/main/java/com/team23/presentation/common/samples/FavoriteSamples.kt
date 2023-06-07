package com.team23.presentation.common.samples

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.presentation.common.samples.RecipeSamples.summarizedRecipeSample
import com.team23.presentation.favorite.models.DisplayType
import com.team23.presentation.favorite.models.FavoriteUiState

object FavoriteSamples {
	class SampleFavoriteStateProvider : PreviewParameterProvider<FavoriteUiState> {
		override val values = sequenceOf(
			FavoriteUiState.Loading,
			FavoriteUiState.Data.Empty,
			FavoriteUiState.Data.WithFavorites(
				displayType = DisplayType.BigCard,
				favorites = listOf(summarizedRecipeSample, summarizedRecipeSample, summarizedRecipeSample)
			)
		)
	}
}