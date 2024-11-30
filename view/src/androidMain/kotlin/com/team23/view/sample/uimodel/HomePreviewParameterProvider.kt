package com.team23.view.sample.uimodel

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState

class HomePreviewParameterProvider: PreviewParameterProvider<HomeUiState> {
    override val values = sequenceOf(
        HomeUiState.Loading,
        HomeUiState.Error(errorUiModel = ErrorUiModel("errorUiModel") {}),
        HomeUiState.Data(recipes = List(6) { summarizedRecipeSample })
    )
}
