package com.team23.presentation.recipe.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.cell.CellLocalPhone
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
	onLocalPhoneClick: () -> Unit,
	onUpdateLocalRecipe: () -> Unit,
	onDeleteLocalRecipe: () -> Unit,
	modifier: Modifier = Modifier,
) {
	Scaffold(
		floatingActionButton = {
			if (recipeUiModel.isLocallySaved) {
				RecipeModifyButton(onUpdateLocalRecipe, onDeleteLocalRecipe)
			}
		}
	) { padding ->
		val density = LocalDensity.current
		val horizontalPadding = 32f
		var yPosition by remember { mutableStateOf(0f) }
		val coeff = (1 - yPosition / (4 * horizontalPadding)).coerceIn(0f, 1f)
		val spaceToAddInPx = with(LocalDensity.current) { horizontalPadding.dp.toPx() } * 2

		Box(modifier = modifier.fillMaxSize()) {
			Column(
				modifier = Modifier
					.verticalScroll(scrollState)
					.padding(padding)
					.padding(horizontal = horizontalPadding.dp, vertical = 16.dp)
					.background(color = MaterialTheme.colorScheme.background)
			) {
				Spacer(modifier = Modifier.height(64.dp + 8.dp))
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
				RecipeImage(
					recipeUiModel, onFavoriteClick, onLocalPhoneClick,
					modifier = Modifier
						.padding(vertical = 8.dp)
						.fillMaxWidth()
						.onGloballyPositioned { layoutCoordinates ->
							yPosition = layoutCoordinates.positionInRoot().y
						}
						.graphicsLayer {
							scaleX = (size.width + spaceToAddInPx * coeff) / size.width
							scaleY = (size.height + spaceToAddInPx * coeff) / size.height
							translationY = -with(density) { horizontalPadding.dp.toPx() } * coeff
						}
				)

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
			AnimatedVisibility(
				visible = yPosition <= 0f,
				enter = fadeIn() + slideInVertically(),
				exit = ExitTransition.None,
			) {
				RecipeImage(
					recipeUiModel, onFavoriteClick, onLocalPhoneClick,
					modifier = Modifier
						.fillMaxWidth()
						.padding(horizontal = horizontalPadding.dp)
						.graphicsLayer {
							scaleX = (size.width + spaceToAddInPx) / size.width
							scaleY = (size.height + spaceToAddInPx) / size.height
							translationY = -with(density) { horizontalPadding.dp.toPx() } * coeff
						}
				)
			}
		}
	}
}

@Composable
private fun RecipeImage(
	recipeUiModel: RecipeUiModel,
	onFavoriteClick: (RecipeUiModel) -> Unit,
	onLocalPhoneClick: () -> Unit,
	modifier: Modifier,
) {
	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
		modifier = modifier,
	) {
		Box {
			NeuracrImage(
				neuracrImageProperty = recipeUiModel.image,
				maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
			)
			if (recipeUiModel.isLocallySaved) {
				CellLocalPhone(
					onIconClick = onLocalPhoneClick,
					modifier = Modifier.align(Alignment.TopStart),
				)
			}
			NeuracrLike(
				isFavorite = recipeUiModel.isFavorite,
				onFavoriteClick = { onFavoriteClick(recipeUiModel) },
				modifier = Modifier.align(Alignment.BottomEnd)
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
				isLocallySaved = true,
			),
			scrollState = rememberScrollState(),
			heightToBeFaded = remember { mutableStateOf(0f) },
			onAddOneServing = {},
			onSubtractOneServing = {},
			currentServingsAmount = "4",
			onValueChanged = {},
			onTagClicked = {},
			onFavoriteClick = {},
			onLocalPhoneClick = {},
			onUpdateLocalRecipe = {},
			onDeleteLocalRecipe = {},
			modifier = Modifier.background(color = Color.White)
		)
	}
}
