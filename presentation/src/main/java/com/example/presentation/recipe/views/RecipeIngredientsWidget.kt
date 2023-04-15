package com.example.presentation.recipe.views

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theming.NeuracrTheme
import com.example.presentation.R

@Composable
fun RecipeIngredientsWidget(modifier: Modifier = Modifier) {
	// TODO GET FROM UIMODEL
	val defaultServingsAmount = 4
	val servingsAmount = remember { mutableStateOf(defaultServingsAmount) }
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
			RecipeServingsWidget(servingsAmount = servingsAmount, defaultServingsAmount = defaultServingsAmount)
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeIngredientsWidgetPreview() {
	NeuracrTheme {
		RecipeIngredientsWidget()
	}
}
