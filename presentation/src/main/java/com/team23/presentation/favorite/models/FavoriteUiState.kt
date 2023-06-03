package com.team23.presentation.favorite.models

import com.team23.presentation.home.models.SummarizedRecipeUiModel

sealed class FavoriteUiState {
	object Loading : FavoriteUiState()

	data class Data(
		val displayType: DisplayType,
		val favorites: List<SummarizedRecipeUiModel>,
	) : FavoriteUiState()
}