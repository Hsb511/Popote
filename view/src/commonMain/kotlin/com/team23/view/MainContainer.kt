package com.team23.view

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.view.ds.scaffold.PopoteScaffold
import com.team23.view.theme.PopoteTheme

@Composable
fun MainContainer() {
    PopoteTheme {
        PopoteScaffold(
            snackbarHostState = SnackbarHostState(),
            scrollState = rememberScrollState(),
            heightToBeFaded = 8.9f,
            title = null,
            navItemProperties = listOf(),
            navigateUp = {},
            drawerState = rememberDrawerState(DrawerValue.Closed),
            openMenu = {},
            closeMenu = {},
            isNavigationEmpty = false

        ) {
            val s =  SummarizedRecipeUiModel(
                id = "",
                imageProperty = ImageProperty.Resource(
                    contentDescription = null,
                    imageRes = "drawable/bretzel.jpg"
                ),
                title = "Bretzels",
                flagProperty = FlagProperty.FRENCH,
                isFavorite = true,
                isLocallySaved = true,
            )
            FavoriteScreen(
                FavoriteUiState.Data.WithFavorites(
                    displayType = DisplayType.SmallCard,
                    favorites = listOf(s, s, s)
                ), {}, {}, {}, {}
            )
        }
    }
}
