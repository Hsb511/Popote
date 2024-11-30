package com.team23.view.sample.uimodel

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState

class FavoritePreviewParameterProvider : PreviewParameterProvider<FavoriteUiState> {
    override val values = sequenceOf(
        FavoriteUiState.Loading,
        FavoriteUiState.Data.Empty,
        FavoriteUiState.Data.WithFavorites(
            displayType = DisplayType.BigCard,
            favorites = listOf(summarizedRecipeSample, summarizedRecipeSample, summarizedRecipeSample)
        )
    )
}
