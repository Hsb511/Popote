package com.team23.view

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.model.uimodel.SearchUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagsRowUiModel
import com.team23.neuracrsrecipes.model.uimodel.TextFieldUiModel
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
            val previewTextFieldSample = TextFieldUiModel(
                searchValue = "Bretzels",
                onValueChange = { },
                label = "Label",
                placeholder = "Placeholder",
                leadingIcon = IconProperty.Vector(Icons.Filled.Search, ""),
            )
            SearchScreen(
                searchUiModel = SearchUiModel(
                    textField = previewTextFieldSample,
                    tagsRow = TagsRowUiModel(
                        tags = listOf(
                            TagUiModel("soup", true),
                            TagUiModel("veggie", true),
                            TagUiModel("cocktail", false),
                            TagUiModel("drink", false),
                            TagUiModel("main", false),
                            TagUiModel("italian", true)
                        ),
                        onTagSelected = { },
                    ),
                    recipes = List(6) { s },
                    onRecipeClick = {},
                    onFavoriteClick = {},
                    onLocalPhoneClick = {},
                ),
                modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
            )
        }
    }
}
