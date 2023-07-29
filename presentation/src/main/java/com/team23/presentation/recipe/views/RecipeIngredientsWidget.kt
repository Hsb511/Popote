package com.team23.presentation.recipe.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.recipe.models.IngredientUiModel
import com.team23.presentation.recipe.models.IngredientsUiModel
import kotlin.text.Typography.bullet

@Composable
fun RecipeIngredientsWidget(
	ingredientUiModel: IngredientsUiModel,
	modifier: Modifier = Modifier
) {
	val density = LocalDensity.current
	val widgetWidth = remember { mutableStateOf(0) }
	val ingredientsList = ingredientUiModel.ingredients
		.map { ingredient ->
			ingredient.quantity?.let {
				"${ingredient.quantity} ${ingredient.label}"
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
				currentServingsAmount = ingredientUiModel.currentServingsAmount,
				onValueChanged = ingredientUiModel.onValueChanged,
				onAddOneServing = ingredientUiModel.onAddOneServing,
				onSubtractOneServing = ingredientUiModel.onSubtractOneServing,
				widgetWidth = widgetWidth,
			)
		}
		Box {
			SelectionContainer {
				Column(modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
					ingredientsList
						.forEach { ingredient ->
							Row {
								Text(
									text = "$bullet",
									modifier = Modifier.width(32.dp)
								)
								Text(
									text = ingredient,
									modifier = Modifier.fillMaxWidth()
								)
							}
						}
				}
			}
			when (ingredientUiModel) {
				is IngredientsUiModel.FromAddScreen -> Button(
					onClick = {},
					modifier = Modifier
						.align(Alignment.BottomEnd)
						.width(with(density) { widgetWidth.value.toDp() })
						.height(32.dp)
				) {
					Icon(
						imageVector = Icons.Filled.Add,
						contentDescription = null,
					)
				}

				is IngredientsUiModel.FromRecipeScreen -> RecipeIngredientsCopyButton(
					ingredientsList = ingredientsList,
					modifier = Modifier.align(Alignment.BottomEnd),
				)
			}
		}
	}
}

@Composable
@Preview(showBackground = true)
fun RecipeIngredientsWidgetPreview() {
	NeuracrTheme {
		RecipeIngredientsWidget(
			IngredientsUiModel.FromRecipeScreen(
				listOf(
					IngredientUiModel("0.5", " - lime"),
					IngredientUiModel("15", " mL - sugar syrup"),
					IngredientUiModel("12", " - raspberry (frozen)"),
					IngredientUiModel("12", " - mint leaf"),
				),
				currentServingsAmount = "4",
				onValueChanged = {},
				onAddOneServing = {},
				onSubtractOneServing = {},
			)
		)
	}
}
