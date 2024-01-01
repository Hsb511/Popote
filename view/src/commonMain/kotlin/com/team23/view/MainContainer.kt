package com.team23.view

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.model.uimodel.DrawerUiModel
import com.team23.neuracrsrecipes.model.uimodel.IngredientUiModel
import com.team23.neuracrsrecipes.model.uimodel.InstructionUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.view.ds.scaffold.PopoteScaffold
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.drawer.ModalMenuDrawer
import com.team23.view.widget.recipe.RecipeContentData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

@Composable
fun MainContainer() {
    PopoteTheme {
        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        PopoteScaffold(
            snackbarHostState = SnackbarHostState(),
            scrollState = rememberScrollState(),
            heightToBeFaded = 8.9f,
            title = null,
            navItemProperties = listOf(),
            navigateUp = {},
            drawerState = drawerState,
            openMenu = { scope.launch(Dispatchers.IO) { drawerState.open() } },
            closeMenu = { scope.launch(Dispatchers.IO) { drawerState.close() } },
            isNavigationEmpty = false

        ) {
            ModalMenuDrawer(
                drawerUiModel = DrawerUiModel("2.0.0"),
                drawerState = drawerState,
            ) {
                RecipeContentData(
                    recipeUiModel = RecipeUiModel(
                        id = "",
                        title = "Bretzels ! Bretzels !",
                        date = "23 Octobre 2023",
                        author = "Guiiiii",
                        tags = listOf("swiss", "bread"),
                        image = ImageProperty.Resource(
                            contentDescription = null,
                            imageRes = "drawable/bretzel.jpg",
                        ),
                        defaultServingsAmount = 4,
                        description = "description",
                        conclusion = "conclusion",
                        ingredients = listOf(
                            IngredientUiModel("0.5", "", "lime"),
                            IngredientUiModel("15", "", "sugar syrup"),
                            IngredientUiModel("12", "", "raspberry (frozen)"),
                            IngredientUiModel("12", "", "mint leaf"),
                        ),
                        instructions = listOf(
                            InstructionUiModel(1, "Boil some water in a pot"),
                            InstructionUiModel(2, "Chop the shallots finely"),
                            InstructionUiModel(
                                3,
                                "Put your salmon in a gratin dish. Season with salt, pepper and some of the shallots. Cover the dish with Cellophane"
                            )
                        ),
                        isFavorite = true,
                        isLocallySaved = true,
                    ),
                    scrollState = rememberScrollState(),
                    heightToBeFaded = remember { mutableStateOf(0f) },
                    onAddOneServing = {},
                    onSubtractOneServing = {},
                    currentServingsAmount = "4",
                    onValueChanged = {},
                    onTagClicked = {},
                    onFavoriteClick = {},
                    onLocalPhoneClick = {},
                    onUpdateLocalRecipe = {},
                    onDeleteLocalRecipe = {},
                    modifier = Modifier.background(color = Color.White)
                )
            }
        }
    }
}
