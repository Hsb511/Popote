package com.team23.presentation.recipe.views

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.like.NeuracrLike
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.recipe.models.IngredientsUiModel
import com.team23.presentation.recipe.models.InstructionUiModel
import com.team23.presentation.recipe.models.InstructionsUiModel
import com.team23.presentation.recipe.models.RecipeUiModel
import com.team23.presentation.recipe.models.ingredientsPreviewSamples

@Composable
fun RecipeContentData(
	recipeUiModel: RecipeUiModel,
	currentServingsAmount: String,
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	onTagClicked: (String) -> Unit,
	onFavoriteClick: (RecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.verticalScroll(scrollState)
			.padding(horizontal = 32.dp, vertical = 16.dp)
			.background(color = MaterialTheme.colorScheme.background)
	) {
		Text(
			text = recipeUiModel.title,
			style = MaterialTheme.typography.displaySmall,
		)
		Divider(modifier = Modifier
			.padding(top = 8.dp)
			.onGloballyPositioned { layoutCoordinates ->
				heightToBeFaded.value = layoutCoordinates.positionInRoot().y
			}
		)
		Text(
			text = with(recipeUiModel) { "$date - ${stringResource(id = R.string.recipe_written_by)} $author" },
			style = MaterialTheme.typography.labelLarge,
		)
		LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			items(recipeUiModel.tags) { tag ->
				ElevatedSuggestionChip(
					colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
						containerColor = MaterialTheme.colorScheme.tertiary,
						labelColor = MaterialTheme.colorScheme.onTertiary,
					),
					onClick = { onTagClicked(tag) },
					label = {
						Text(text = tag)
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
			Box {
				NeuracrImage(
					neuracrImageProperty = recipeUiModel.image,
					maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
				)
				NeuracrLike(
					isFavorite = recipeUiModel.isFavorite,
					onFavoriteClick = { onFavoriteClick(recipeUiModel) },
					modifier = Modifier.align(Alignment.BottomEnd)
				)
			}
		}
		Text(
			text = stringResource(id = R.string.recipe_ingredients_title),
			style = MaterialTheme.typography.headlineSmall,
			modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
		)
		RecipeIngredientsWidget(
			IngredientsUiModel.FromRecipeScreen(
				ingredients = recipeUiModel.ingredients,
				currentServingsAmount = currentServingsAmount,
				onValueChanged = onValueChanged,
				onAddOneServing = onAddOneServing,
				onSubtractOneServing = onSubtractOneServing,
			),
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
		RecipeInstructionsWidget(InstructionsUiModel.FromRecipeScreen(recipeUiModel.instructions))
		Text(
			text = recipeUiModel.conclusion,
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.padding(top = 16.dp),
		)
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeContentDataPreview() {
	NeuracrTheme {
		RecipeContentData(
			recipeUiModel = RecipeUiModel(
				id = "",
				title = "Bretzels ! Bretzels !",
				date = "23 Octobre 2023",
				author = "Guiiiii",
				tags = listOf("swiss", "bread"),
				image = NeuracrImageProperty.Resource(null, com.team23.design_system.R.drawable.bretzel),
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
			),
			scrollState = rememberScrollState(),
			heightToBeFaded = remember { mutableStateOf(0f) },
			onAddOneServing = {},
			onSubtractOneServing = {},
			currentServingsAmount = "4",
			onValueChanged = {},
			onTagClicked = {},
			onFavoriteClick = {},
			modifier = Modifier.background(color = Color.White)
		)
	}
}
