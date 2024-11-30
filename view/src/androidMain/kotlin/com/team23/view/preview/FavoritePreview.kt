package com.team23.view.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
import com.team23.view.widget.favorite.FavoriteItem

@Composable
@Preview(showBackground = true)
fun FavoriteItemPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
    PopoteTheme {
        FavoriteItem(
            displayType = displayType,
            summarizedRecipe = summarizedRecipeSample,
            onFavoriteClick = {},
            onRecipeClick = {},
            onLocalPhoneClick = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun FavoriteDataScreenPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
    PopoteTheme {
        FavoriteDataScreen(
            state = FavoriteUiState.Data.WithFavorites(
                displayType = displayType,
                favorites = listOf(
                    summarizedRecipeSample,
                    summarizedRecipeSample,
                    summarizedRecipeSample
                ),
            ),
            onRecipeClick = {},
            onFavoriteClick = {},
            onDisplayClick = {},
            onLocalPhoneClick = {},
        )
    }
}


@Composable
@Preview(showSystemUi = true)
fun FavoriteDataEmptyScreenPreview() {
    PopoteTheme {
        FavoriteDataEmptyScreen()
    }
}

@Composable
@Preview(showSystemUi = true)
fun FavoriteScreenPreview(@PreviewParameter(FavoritePreviewParameterProvider::class) favoriteUiState: FavoriteUiState) {
    PopoteTheme {
        FavoriteScreen(favoriteUiState, {}, {}, {}, {})
    }
}
