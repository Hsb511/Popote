package com.team23.view.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.team23.neuracrsrecipes.model.uimodel.InstructionUiModel
import com.team23.neuracrsrecipes.model.uimodel.InstructionsUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.view.sample.property.resourceImagePreviewSample
import com.team23.view.sample.uimodel.ingredientsPreviewSamples
import com.team23.view.sample.uimodel.ingredientsUiModelPreviewSamples
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.recipe.RecipeAddButton
import com.team23.view.widget.recipe.RecipeContentData
import com.team23.view.widget.recipe.RecipeContentLoading
import com.team23.view.widget.recipe.RecipeIngredientsColumn
import com.team23.view.widget.recipe.RecipeIngredientsCopyButton
import com.team23.view.widget.recipe.RecipeIngredientsWidget
import com.team23.view.widget.recipe.RecipeInstructionsWidget
import com.team23.view.widget.recipe.RecipeModifyButton
import com.team23.view.widget.recipe.RecipeServingsWidget


@Composable
@Preview(showBackground = true)
fun RecipeIngredientsColumnPreview() {
    MaterialTheme {
        RecipeIngredientsColumn(
            ingredientsUiModelPreviewSamples
        )
    }
}


@Composable
@Preview(showBackground = true)
fun RecipeIngredientsCopyButtonPreview() {
    MaterialTheme {
        RecipeIngredientsCopyButton(
            ingredientsList = listOf(
                "0.5 - lime",
                "15 mL - sugar syrup",
                "12 - raspberry (frozen)",
                "12 - mint leaf",
            ),
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RecipeServingsWidgetPreview() {
    PopoteTheme {
        RecipeServingsWidget(
            currentServingsAmount = "4",
            onValueChanged = {},
            onAddOneServing = {},
            onSubtractOneServing = {},
            widgetWidth = remember { mutableIntStateOf(100) }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun RecipeAddIngredientButtonPreview() {
    PopoteTheme {
        RecipeAddButton(onAddClick = {}, widgetWidth = 32)
    }
}


@Composable
@Preview(showSystemUi = true)
fun RecipeInstructionsWidgetPreview() {
    PopoteTheme {
        RecipeInstructionsWidget(
            InstructionsUiModel.FromAddScreen(listOf(
                InstructionUiModel(1, "Boil some water in a pot"),
                InstructionUiModel(2, "Chop the shallots finely"),
                InstructionUiModel(
                    3,
                    "Put your salmon in a gratin dish. Season with salt, pepper and some of the shallots. Cover the dish with Cellophane"
                )
            ), {}, {}) {},
            modifier = Modifier.background(Color.White),
            addButtonWidth = 100,
        )
    }
}


@Composable
@Preview(showBackground = true)
fun RecipeIngredientsWidgetPreview() {
    PopoteTheme {
        RecipeIngredientsWidget(
            ingredientsUiModelPreviewSamples,
        )
    }
}


@Composable
@Preview(showBackground = true)
fun RecipeModifyButtonPreview() {
    PopoteTheme {
        RecipeModifyButton({}, {})
    }
}

@Composable
@Preview(showSystemUi = true)
fun RecipeContentLoadingPreview() {
    PopoteTheme {
        RecipeContentLoading()
    }
}

@Composable
@Preview(showSystemUi = true)
fun RecipeContentDataPreview() {
    PopoteTheme {
        RecipeContentData(
            recipeUiModel = RecipeUiModel(
                id = "",
                title = "Bretzels ! Bretzels !",
                date = "23 Octobre 2023",
                author = "Guiiiii",
                tags = listOf("swiss", "bread"),
                image = resourceImagePreviewSample,
                defaultServingsAmount = 4,
                description = "description",
                conclusion = "conclusion",
                ingredients = ingredientsPreviewSamples,
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
