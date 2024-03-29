package com.team23.presentation.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.cell.NeuracrCell
import com.team23.design_system.display.DisplayType
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.common.samples.RecipeSamples.summarizedRecipeSample
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.home.models.toNeuracrCellProperty
import com.team23.presentation.search.SearchSamples.previewTextFieldSample
import com.team23.presentation.search.models.SearchUiModel
import com.team23.presentation.search.models.TagUiModel
import com.team23.presentation.search.models.TagsRowUiModel
import com.team23.presentation.search.models.TextFieldUiModel
import com.team23.presentation.search.views.SearchTagsRow
import com.team23.presentation.search.views.SearchTextField

@Composable
fun SearchScreen(
	snackbarHostState: SnackbarHostState,
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
	selectedTag: String? = null,
	searchViewModel: SearchViewModel = hiltViewModel()
) {
	val context = LocalContext.current
	searchViewModel.selectedTag = selectedTag
	SearchScreen(
		searchUiModel = SearchUiModel(
			textField = TextFieldUiModel(
				searchValue = searchViewModel.searchValue.value,
				onValueChange = { newValue -> searchViewModel.onValueChange(newValue) },
				interactionSource = remember { MutableInteractionSource() },
				label = R.string.search_textfield_label,
				placeholder = R.string.search_textfield_placeholder,
				leadingIcon = TextFieldUiModel.IconUiModel.Vector(Icons.Filled.Search),
			),
			tagsRow = TagsRowUiModel(
				tags = searchViewModel.tags.collectAsState().value,
				onTagSelected = { tag -> searchViewModel.onTagSelected(tag) },
			),
			recipes = searchViewModel.recipes.collectAsState().value,
			onRecipeClick = onRecipeClick,
			onFavoriteClick = { recipe -> searchViewModel.favoriteClick(recipe, snackbarHostState, context) },
			onLocalPhoneClick = { searchViewModel.onLocalPhoneClick(snackbarHostState, context) },
		),
		modifier = modifier,
	)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun SearchScreen(
	searchUiModel: SearchUiModel,
	modifier: Modifier = Modifier
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(8.dp),
		modifier = modifier
			.padding(start = 16.dp, top = 8.dp, end = 16.dp)
			.fillMaxSize()
	) {
		Spacer(modifier = Modifier.height(64.dp + 16.dp))
		SearchTextField(searchUiModel.textField)

		SearchTagsRow(searchUiModel.tagsRow)

		LazyVerticalGrid(
			columns = GridCells.Adaptive(300.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			contentPadding = PaddingValues(bottom = 16.dp),
			modifier = modifier.fillMaxSize()
		) {
			items(searchUiModel.recipes) { recipe ->
				NeuracrCell(
					neuracrCellProperty = recipe.toNeuracrCellProperty(
						displayType = DisplayType.List,
						onFavoriteClick = {
							searchUiModel.onFavoriteClick(recipe)
						},
						onLocalPhoneClick = searchUiModel.onLocalPhoneClick,
					),
					modifier = Modifier
						.animateItemPlacement()
						.clickable {
							searchUiModel.onRecipeClick(recipe)
						}
				)
			}
		}
	}
}

@Composable
@Preview(showSystemUi = true)
private fun SearchScreenPreview() {
	NeuracrTheme {
		SearchScreen(
			searchUiModel = SearchUiModel(
				textField = previewTextFieldSample,
				tagsRow = TagsRowUiModel(
					tags = listOf(
						TagUiModel("soup", true),
						TagUiModel("veggie", true),
						TagUiModel("cocktail", false),
						TagUiModel("drink", false),
						TagUiModel("main", false),
						TagUiModel("italian", true)
					),
					onTagSelected = { },
				),
				recipes = List(6) { summarizedRecipeSample },
				onRecipeClick = {},
				onFavoriteClick = {},
				onLocalPhoneClick = {},
			),
			modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
		)
	}
}
