package com.team23.view.preview

import androidx.compose.foundation.background
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
import com.team23.view.widget.recipe.RecipeIngredientsCopyButton
import com.team23.view.sample.uimodel.ingredientsUiModelPreviewSamples
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.recipe.RecipeAddButton
import com.team23.view.widget.recipe.RecipeIngredientsColumn
import com.team23.view.widget.recipe.RecipeIngredientsWidget
import com.team23.view.widget.recipe.RecipeInstructionsWidget
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
