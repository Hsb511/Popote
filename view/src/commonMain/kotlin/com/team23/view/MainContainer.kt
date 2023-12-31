package com.team23.view

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.model.uimodel.AddRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
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
            AddScreen(
                addRecipe = AddRecipeUiModel(
                    recipe = RecipeUiModel(
                        id = "convenire",
                        title = "quo",
                        date = "varius",
                        author = "sed",
                        tags = listOf("a", "b", "cccc"),
                        image = ImageProperty.Resource(
                            contentDescription = null,
                            imageRes = "drawable/bretzel.jpg"
                        ),
                        ingredients = listOf(),
                        instructions = listOf(),
                        defaultServingsAmount = 5231,
                        description = "quisque",
                        conclusion = "mandamus",
                        isFavorite = true,
                        isLocallySaved = true
                    ),
                    {}, {}, {}, {}, {}, {}, {}, { _, _ -> }, {}, {}, {}, {}, {}, {}, {}, {},
                ),
                scrollState = rememberScrollState(),
                heightToBeFaded = remember { mutableFloatStateOf(120f) },
                allTags = listOf("a", "bbbb", "23232323"),
            )
        }
    }
}
