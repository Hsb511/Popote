package com.team23.neuracrsrecipes.model.uistate

import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

sealed class FavoriteUiState {
	data object Loading : FavoriteUiState()

	sealed class Data: FavoriteUiState() {
		data object Empty : Data()
		data class WithFavorites(
			val displayType: DisplayType,
			val favorites: List<SummarizedRecipeUiModel>,
		) : Data()
	}

}