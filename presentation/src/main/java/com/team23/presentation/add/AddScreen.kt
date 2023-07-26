package com.team23.presentation.add

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.add.models.AddRecipeUiModel
import com.team23.presentation.add.views.AddTextField

@Composable
fun AddScreen(
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	title: MutableState<String?>,
	modifier: Modifier = Modifier,
	addViewModel: AddViewModel = hiltViewModel(),
) {
	AddScreen(
		addRecipe = addViewModel.initialRecipe,
		scrollState = scrollState,
		heightToBeFaded = heightToBeFaded,
		title = title,
		modifier = modifier,
	)
}

@Composable
fun AddScreen(
	addRecipe: AddRecipeUiModel,
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	title: MutableState<String?>,
	modifier: Modifier = Modifier,
) {
	Column(
		modifier = modifier
			.fillMaxSize()
			.verticalScroll(scrollState)
			.padding(horizontal = 32.dp, vertical = 16.dp)
			.background(color = MaterialTheme.colorScheme.background)
	) {
		AddTextField(
			initialText = title.value,
			onTextChange = addRecipe.onTitleChange,
			style = MaterialTheme.typography.displaySmall,
			modifier = Modifier.fillMaxWidth()
		)

		Divider(modifier = Modifier
			.padding(top = 8.dp)
			.onGloballyPositioned { layoutCoordinates ->
				heightToBeFaded.value = layoutCoordinates.positionInRoot().y
			}
		)
		Row {
			Text(
				text = "${addRecipe.recipe.date} - ${stringResource(id = R.string.recipe_written_by)}",
				style = MaterialTheme.typography.labelLarge,
			)
			AddTextField(
				initialText = addRecipe.recipe.author,
				onTextChange = addRecipe.onAuthorChange,
				style = MaterialTheme.typography.labelLarge,
				modifier = Modifier.padding(start = 8.dp),
			)
		}

		/*

		Card(
			elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
			modifier = Modifier
				.padding(vertical = 8.dp)
				.fillMaxWidth(),
		) { }
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
		 */
	}
}

@Composable
@Preview(showSystemUi = true)
fun AddScreenPreview() {
	NeuracrTheme {
		AddScreen(
			scrollState = rememberScrollState(),
			heightToBeFaded = remember { mutableStateOf(120f) },
			title = remember { mutableStateOf("") },
		)
	}
}
