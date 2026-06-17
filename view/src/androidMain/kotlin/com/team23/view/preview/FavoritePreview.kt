package com.team23.view.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.view.navigation.screen.FavoriteScreen
import com.team23.view.sample.property.SampleDisplayTypeProvider
import com.team23.view.sample.uimodel.FavoritePreviewParameterProvider
import com.team23.view.sample.uimodel.summarizedRecipeSample
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.favorite.FavoriteDataEmptyScreen
import com.team23.view.widget.favorite.FavoriteDataScreen

@Composable
@PreviewWithBackground
fun FavoriteDataScreenPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
    PopoteTheme {
        FavoriteDataScreen(
            state = FavoriteUiState.Data.WithFavorites(
                displayType = displayType,
                favorites = listOf(
                    summarizedRecipeSample.copy(id = "1"),
                    summarizedRecipeSample.copy(id = "2"),
                    summarizedRecipeSample.copy(id = "3"),
                ),
            ),
            onRecipeClick = {},
            onFavoriteClick = {},
            onDisplayClick = {},
            onLocalPhoneClick = {},
            onRemoveAllClick = {},
        )
    }
}


@Composable
@PreviewWithSystemUi
fun FavoriteDataEmptyScreenPreview() {
    PopoteTheme {
        FavoriteDataEmptyScreen()
    }
}

@Composable
@PreviewWithSystemUi
fun FavoriteScreenPreview(@PreviewParameter(FavoritePreviewParameterProvider::class) favoriteUiState: FavoriteUiState) {
    PopoteTheme {
        FavoriteScreen(favoriteUiState, {}, {}, {}, {}, {})
    }
}
