package com.team23.presentation.recipe.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.recipe.models.IngredientUiModel
import com.team23.presentation.recipe.models.InstructionUiModel
import com.team23.presentation.recipe.models.RecipeUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeContentData(
	recipeUiModel: RecipeUiModel,
	currentServingsAmount: String,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(modifier = modifier.fillMaxSize()) {
		Column(
			modifier = Modifier
				.weight(weight = 1f, fill = false)
				.verticalScroll(rememberScrollState())
				.padding(horizontal = 32.dp, vertical = 16.dp)
				.background(color = MaterialTheme.colorScheme.background)
		) {
			Text(
				text = recipeUiModel.title,
				style = MaterialTheme.typography.displaySmall,
			)
			Divider(modifier = Modifier.padding(top = 8.dp))
			Text(
				text = with(recipeUiModel) { "$date - ${stringResource(id = R.string.recipe_written_by)} $author" },
				style = MaterialTheme.typography.labelLarge,
			)
			LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
				items(recipeUiModel.tags) { tag ->
					ElevatedSuggestionChip(
						onClick = { /*TODO*/ },
						label = {
							Text(
								text = tag,
								color = MaterialTheme.colorScheme.onSurface,
							)
						}
					)
				}
			}
			Card(
				elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
				modifier = Modifier
					.padding(vertical = 8.dp)
					.fillMaxWidth(),
			) {
				NeuracrImage(
					neuracrImageProperty = recipeUiModel.image,
					maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
				)
			}
			Text(
				text = stringResource(id = R.string.recipe_ingredients_title),
				style = MaterialTheme.typography.headlineSmall,
				modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
			)
			RecipeIngredientsWidget(
				ingredients = recipeUiModel.ingredients,
				currentServingsAmount = currentServingsAmount,
				onValueChanged = onValueChanged,
				onAddOneServing = onAddOneServing,
				onSubtractOneServing = onSubtractOneServing,
			)
			Divider(modifier = Modifier.padding(top = 8.dp))
			Text(
				text = recipeUiModel.description,
				style = MaterialTheme.typography.bodyMedium,
			)
			Text(
				text = stringResource(id = R.string.recipe_instructions_title),
				style = MaterialTheme.typography.headlineSmall,
				modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
			)
			RecipeInstructionsWidget(recipeUiModel.instructions)
			Text(
				text = recipeUiModel.conclusion,
				style = MaterialTheme.typography.bodyMedium,
				modifier = Modifier.padding(top = 16.dp),
			)
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeContentDataPreview() {
	NeuracrTheme {
		RecipeContentData(
			recipeUiModel = RecipeUiModel(
				title = "Bretzels ! Bretzels !",
				date = "23 Octobre 2023",
				author = "Guiiiii",
				tags = listOf("swiss", "bread"),
				image = NeuracrImageProperty.Resource(null, com.team23.design_system.R.drawable.bretzel),
				defaultServingsAmount = 4,
				description = "description",
				conclusion = "conclusion",
				ingredients = listOf(
					IngredientUiModel("0.5", " - lime"),
					IngredientUiModel("15", " mL - sugar syrup"),
					IngredientUiModel("12", " - raspberry (frozen)"),
					IngredientUiModel("12", " - mint leaf"),
				),
				instructions = listOf(
					InstructionUiModel(1, "Boil some water in a pot"),
					InstructionUiModel(2, "Chop the shallots finely"),
					InstructionUiModel(3, "Put your salmon in a gratin dish. Season with salt, pepper and some of the shallots. Cover the dish with Cellophane"
					)
				)
			),
			onAddOneServing = {},
			onSubtractOneServing = {},
			currentServingsAmount = "4",
			onValueChanged = {},
		)
	}
}