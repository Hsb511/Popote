package com.example.presentation.recipe.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.image.NeuracrImage
import com.example.design_system.image.NeuracrImageProperty
import com.example.design_system.theming.NeuracrTheme
import com.example.presentation.R
import com.example.presentation.recipe.models.RecipeUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeContentData(
	recipeUiModel: RecipeUiModel,
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
			LazyRow {
				items(recipeUiModel.tags) { tag ->
					SuggestionChip(
						onClick = { /*TODO*/ },
						label = { Text(text = tag) }
					)
				}
			}
			NeuracrImage(
				neuracrImageProperty = recipeUiModel.image,
				maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
				modifier = Modifier
					.padding(vertical = 8.dp)
					.fillMaxWidth(),
			)
			Text(
				text = stringResource(id = R.string.recipe_ingredients_title),
				style = MaterialTheme.typography.titleLarge,
			)
			RecipeIngredientsWidget()
			Divider(modifier = Modifier.padding(top = 8.dp))
			Text(text = recipeUiModel.description)
			Text(
				text = stringResource(id = R.string.recipe_instructions_title),
				style = MaterialTheme.typography.titleLarge,
			)
			OutlinedCard(
				colors = CardDefaults.outlinedCardColors(
					containerColor = Color.Transparent,
					contentColor = MaterialTheme.colorScheme.onBackground,
				),
				border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
			) {
				Text(text = "Instructions list")
			}
			Text(text = recipeUiModel.conclusion)
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
				image = NeuracrImageProperty.Resource(null, com.example.design_system.R.drawable.bretzel),
				servingsAmount = 4,
				description = "description",
				conclusion = "conclusion",

				)
		)
	}
}
