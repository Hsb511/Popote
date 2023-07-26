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
import com.team23.presentation.add.views.AddTagSection
import com.team23.presentation.add.views.AddTextField
import com.team23.presentation.recipe.views.RecipeIngredientsWidget
import com.team23.presentation.recipe.views.RecipeInstructionsWidget

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
		allTags = addViewModel.tags.collectAsState().value,
		scrollState = scrollState,
		heightToBeFaded = heightToBeFaded,
		title = title,
		modifier = modifier,
	)
}

@Composable
fun AddScreen(
	addRecipe: AddRecipeUiModel,
	allTags: List<String>,
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
			placeholder = "Recipe title",
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
				initialText = addRecipe.recipe.author,
				onTextChange = addRecipe.onAuthorChange,
				style = MaterialTheme.typography.labelLarge,
				placeholder = "Author's name",
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
			ingredients = addRecipe.recipe.ingredients,
			currentServingsAmount = addRecipe.recipe.defaultServingsAmount.toString(),
			onValueChanged = addRecipe.onServingsAmountChange,
			onAddOneServing = addRecipe.onAddOneServing,
			onSubtractOneServing = addRecipe.onSubtractOneServing,
		)
		Divider(modifier = Modifier.padding(top = 8.dp))

		AddTextField(
			initialText = addRecipe.recipe.description,
			onTextChange = addRecipe.onDescriptionChange,
			style = MaterialTheme.typography.bodyMedium,
			placeholder = "Description of your recipe, advice for the cooking or cooking time, ...",
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

		RecipeInstructionsWidget(addRecipe.recipe.instructions)

		AddTextField(
			initialText = addRecipe.recipe.conclusion,
			onTextChange = addRecipe.onConclusionChange,
			style = MaterialTheme.typography.bodyMedium,
			placeholder = "A conclusion to your recipe, sides you can cook it with or preparation idea",
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
			title = remember { mutableStateOf("") },
		)
	}
}
