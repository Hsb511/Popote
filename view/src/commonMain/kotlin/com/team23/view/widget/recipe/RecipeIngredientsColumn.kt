package com.team23.view.widget.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.uimodel.IngredientsUiModel
import com.team23.view.Res
import com.team23.view.add_recipe_ingredient_label
import com.team23.view.add_recipe_ingredient_quantity
import com.team23.view.add_recipe_ingredient_unit
import com.team23.view.widget.add.AddTextField
import org.jetbrains.compose.resources.stringResource

@Composable
fun RecipeIngredientsColumn(
    ingredientsUiModel: IngredientsUiModel,
    modifier: Modifier = Modifier,
) {
    SelectionContainer {
        Column(
            modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = if (ingredientsUiModel is IngredientsUiModel.FromAddScreen) 66.dp else 16.dp
            ),
        ) {
            ingredientsUiModel.ingredients
                .forEachIndexed { index, ingredient ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "${Typography.bullet}",
                            modifier = Modifier.width(if (ingredientsUiModel is IngredientsUiModel.FromAddScreen) 16.dp else 32.dp)
                        )
                        when (ingredientsUiModel) {
                            is IngredientsUiModel.FromRecipeScreen -> Text(
                                text = ingredient.quantity
                                    ?.let { quantity -> "$quantity ${ingredient.unit?.let { "$it " } ?: ""}- ${ingredient.label}" }
                                    ?: ingredient.label,
                                modifier = Modifier.fillMaxWidth()
                            )

                            is IngredientsUiModel.FromAddScreen -> {
                                AddTextField(
                                    text = ingredient.quantity,
                                    onTextChange = { quantity ->
                                        ingredientsUiModel.onUpdateIngredient(
                                            ingredient.copy(
                                                quantity = quantity
                                            ), index
                                        )
                                    },
                                    style = MaterialTheme.typography.bodyLarge,
                                    placeholder = stringResource(Res.string.add_recipe_ingredient_quantity),
                                    singleLine = true,
                                    keyboardType = KeyboardType.Decimal,
                                    modifier = Modifier.width(50.dp)
                                )
                                AddTextField(
                                    text = ingredient.unit,
                                    onTextChange = { unit ->
                                        ingredientsUiModel.onUpdateIngredient(
                                            ingredient.copy(unit = unit),
                                            index
                                        )
                                    },
                                    style = MaterialTheme.typography.bodyLarge,
                                    placeholder = stringResource(Res.string.add_recipe_ingredient_unit),
                                    singleLine = true,
                                    modifier = Modifier.width(50.dp)
                                )
                                AddTextField(
                                    text = ingredient.label,
                                    onTextChange = { label ->
                                        ingredientsUiModel.onUpdateIngredient(
                                            ingredient.copy(
                                                label = label
                                            ), index
                                        )
                                    },
                                    style = MaterialTheme.typography.bodyLarge,
                                    placeholder = stringResource(Res.string.add_recipe_ingredient_label),
                                    singleLine = true,
                                    modifier = Modifier.weight(6f)
                                )
                                IconButton(
                                    onClick = { ingredientsUiModel.onDeleteIngredient(index) },
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Delete,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    }
                }
        }
    }
}
