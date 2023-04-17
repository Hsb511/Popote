package com.example.presentation.recipe.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.design_system.theming.NeuracrTheme
import com.example.presentation.R
import kotlin.text.Typography.bullet

@Composable
fun RecipeIngredientsWidget(
	ingredients: List<String>,
	modifier: Modifier = Modifier
) {
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
		Column(modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
			ingredients.forEach { ingredient ->
				val paragraphStyle = ParagraphStyle(textIndent = TextIndent(restLine = 12.sp))
				Text(
					buildAnnotatedString {
						withStyle(style = paragraphStyle) {
							append(bullet)
							append("\t\t")
							append(ingredient)
						}
					}
				)
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
				"0.5 - lime",
				"15 mL - sugar syrup",
				"12 - raspberry (frozen)",
				"12 - mint leaf",
			)
		)
	}
}
