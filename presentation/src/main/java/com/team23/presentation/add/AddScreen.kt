package com.team23.presentation.add

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
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
import com.team23.presentation.add.views.AddImageButton
import com.team23.presentation.add.views.AddSaveButton
import com.team23.presentation.add.views.AddTagSection
import com.team23.presentation.add.views.AddTextField
import com.team23.presentation.recipe.models.IngredientsUiModel
import com.team23.presentation.recipe.views.RecipeIngredientsWidget
import com.team23.presentation.recipe.views.RecipeInstructionsWidget

@Composable
fun AddScreen(
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
	modifier: Modifier = Modifier,
	addViewModel: AddViewModel = hiltViewModel(),
) {
	Scaffold(
		floatingActionButton = {
			AddSaveButton { addViewModel.onSaveButtonClick() }
		}
	) { padding ->
		AddScreen(
			addRecipe = addViewModel.recipe.collectAsState().value,
			allTags = addViewModel.tags.collectAsState().value,
			scrollState = scrollState,
			heightToBeFaded = heightToBeFaded,
			modifier = modifier.padding(padding),
		)
	}
}

@Composable
fun AddScreen(
	addRecipe: AddRecipeUiModel,
	allTags: List<String>,
	scrollState: ScrollState,
	heightToBeFaded: MutableState<Float>,
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
			text = addRecipe.recipe.title,
			onTextChange = addRecipe.onTitleChange,
			style = MaterialTheme.typography.displaySmall,
			placeholder = stringResource(id = R.string.add_recipe_title),
			singleLine = true,
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
				text = addRecipe.recipe.author,
				onTextChange = addRecipe.onAuthorChange,
				style = MaterialTheme.typography.labelLarge,
				placeholder = stringResource(id = R.string.add_recipe_author_name),
				singleLine = true,
				modifier = Modifier.padding(start = 8.dp),
			)
		}

		AddTagSection(
			allTags = allTags,
			onAddTag = addRecipe.onAddTag,
			onRemoveTag = addRecipe.onRemoveTag,
		)

		AddImageButton(
			modifier = Modifier
				.padding(vertical = 8.dp)
				.fillMaxWidth(),
		)

		Text(
			text = stringResource(id = R.string.recipe_ingredients_title),
			style = MaterialTheme.typography.headlineSmall,
			modifier = Modifier.padding(top = 16.dp, bottom = 4.dp),
		)

		RecipeIngredientsWidget(
			IngredientsUiModel.FromAddScreen(
				ingredients = addRecipe.recipe.ingredients,
				currentServingsAmount = addRecipe.recipe.defaultServingsAmount.toString(),
				onValueChanged = addRecipe.onServingsAmountChange,
				onAddOneServing = addRecipe.onAddOneServing,
				onSubtractOneServing = addRecipe.onSubtractOneServing,
				onAddIngredient = addRecipe.onAddIngredient,
				onDeleteIngredient = addRecipe.onDeleteIngredient,
				onUpdateIngredient = addRecipe.onUpdateIngredient,
			)
		)
		Divider(modifier = Modifier.padding(top = 8.dp))

		AddTextField(
			text = addRecipe.recipe.description,
			onTextChange = addRecipe.onDescriptionChange,
			style = MaterialTheme.typography.bodyMedium,
			placeholder = stringResource(id = R.string.add_recipe_description),
			singleLine = false,
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(min = 64.dp),
		)

		Text(
			text = stringResource(id = R.string.recipe_instructions_title),
			style = MaterialTheme.typography.headlineSmall,
			modifier = Modifier.padding(top = 4.dp, bottom = 8.dp),
		)

		RecipeInstructionsWidget(
			instructions = addRecipe.recipe.instructions,
			modifier = Modifier
				.fillMaxWidth()
				.padding(bottom = 16.dp),
		)

		AddTextField(
			text = addRecipe.recipe.conclusion,
			onTextChange = addRecipe.onConclusionChange,
			style = MaterialTheme.typography.bodyMedium,
			placeholder = stringResource(id = R.string.add_recipe_conclusion),
			singleLine = false,
			modifier = Modifier
				.fillMaxWidth()
				.heightIn(min = 64.dp),
		)
	}
}

@Composable
@Preview(showSystemUi = true)
fun AddScreenPreview() {
	NeuracrTheme {
		AddScreen(
			scrollState = rememberScrollState(),
			heightToBeFaded = remember { mutableStateOf(120f) },
		)
	}
}
