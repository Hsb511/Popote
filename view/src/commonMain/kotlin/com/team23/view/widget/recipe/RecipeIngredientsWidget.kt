package com.team23.view.widget.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.uimodel.IngredientsUiModel

@Composable
fun RecipeIngredientsWidget(
    ingredientsUiModel: IngredientsUiModel,
    modifier: Modifier = Modifier,
    addButtonWidth: MutableState<Int> = remember { mutableStateOf(100) },
) {
    val ingredientsList = ingredientsUiModel.ingredients
        .map { ingredient ->
            ingredient.quantity?.let { quantity ->
                ingredient.unit?.let { unit ->
                    "$quantity $unit - ${ingredient.label}"
                } ?: "$quantity ${ingredient.label}"
            } ?: ingredient.label
        }
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary,
        ),
        modifier = modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            RecipeServingsWidget(
                currentServingsAmount = ingredientsUiModel.currentServingsAmount,
                onValueChanged = ingredientsUiModel.onValueChanged,
                onAddOneServing = ingredientsUiModel.onAddOneServing,
                onSubtractOneServing = ingredientsUiModel.onSubtractOneServing,
                widgetWidth = addButtonWidth,
            )
        }
        Box {
            RecipeIngredientsColumn(ingredientsUiModel = ingredientsUiModel)

            when (ingredientsUiModel) {
                is IngredientsUiModel.FromAddScreen -> RecipeAddButton(
                    onAddClick = ingredientsUiModel.onAddIngredient,
                    widgetWidth = addButtonWidth.value,
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(bottom = 16.dp),
                )

                is IngredientsUiModel.FromRecipeScreen -> RecipeIngredientsCopyButton(
                    ingredientsList = ingredientsList,
                    modifier = Modifier.align(Alignment.BottomEnd),
                )
            }
        }
    }
}
