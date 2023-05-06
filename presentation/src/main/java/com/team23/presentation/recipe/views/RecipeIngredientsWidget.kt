package com.team23.presentation.recipe.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
	ElevatedCard(
		colors = CardDefaults.cardColors(
			containerColor = MaterialTheme.colorScheme.secondary,
			contentColor = MaterialTheme.colorScheme.onSecondary,
		),
		modifier = modifier
			.padding(vertical = 8.dp)
			.fillMaxWidth()
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.padding(all = 16.dp)
		) {
			Text(
				text = stringResource(id = R.string.recipe_number_of_servings),
				modifier = Modifier.weight(1f)
			)
			RecipeServingsWidget(
				currentServingsAmount = currentServingsAmount,
				onValueChanged = onValueChanged,
				onAddOneServing = onAddOneServing,
				onSubtractOneServing = onSubtractOneServing,
			)
		}
		Column(modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
			ingredients
				.map { ingredient ->
					ingredient.quantity?.let {
						"${ingredient.quantity} ${ingredient.label}"
					} ?: ingredient.label
				}
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
}

@Composable
@Preview(showSystemUi = true)
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