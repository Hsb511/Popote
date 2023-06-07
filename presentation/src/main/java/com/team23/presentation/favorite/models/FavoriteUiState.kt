package com.team23.presentation.favorite.models

import com.team23.presentation.home.models.SummarizedRecipeUiModel

sealed class FavoriteUiState {
	object Loading : FavoriteUiState()

	sealed class Data: FavoriteUiState() {
		object Empty : Data()
		data class WithFavorites(
			val displayType: DisplayType,
			val favorites: List<SummarizedRecipeUiModel>,
		) : Data()
	}

}