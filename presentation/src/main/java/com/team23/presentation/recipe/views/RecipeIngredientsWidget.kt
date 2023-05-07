package com.team23.presentation.recipe.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.recipe.models.IngredientUiModel
import kotlin.text.Typography.bullet

@Composable
fun RecipeIngredientsWidget(
	ingredients: List<IngredientUiModel>,
	currentServingsAmount: String,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	modifier: Modifier = Modifier
) {
	val clipboardManager = LocalClipboardManager.current
	val ingredientsList = ingredients
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
				currentServingsAmount = currentServingsAmount,
				onValueChanged = onValueChanged,
				onAddOneServing = onAddOneServing,
				onSubtractOneServing = onSubtractOneServing,
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
			IconButton(
				onClick = {
					clipboardManager.setText(AnnotatedString(
						ingredientsList.joinToString(prefix = " $bullet ", separator= "\n $bullet ")
					))
				},
				modifier = Modifier
					.offset(x = 8.dp, y = 8.dp)
					.align(Alignment.BottomEnd)
			) {
				Icon(
					painter = painterResource(id = R.drawable.ic_content_copy),
					contentDescription = stringResource(id = R.string.recipe_copy_to_clipboard_a11y),
					tint = MaterialTheme.colorScheme.surface.copy(alpha = 0.69f),
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
	}
}
