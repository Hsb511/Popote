package com.team23.presentation.search

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.team23.design_system.R
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.home.models.SummarizedRecipeUiModel
import com.team23.presentation.search.models.SearchUiModel
import com.team23.presentation.search.models.TagUiModel
import com.team23.presentation.search.views.SearchFilterChip
import com.team23.presentation.search.views.SearchRecipeCard

@Composable
fun SearchScreen(
	onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier,
	searchViewModel: SearchViewModel = hiltViewModel()
) {
	SearchScreen(
		searchUiModel = SearchUiModel(
			searchValue = searchViewModel.searchValue.value,
			onValueChange = { newValue -> searchViewModel.onValueChange(newValue) },
			tags = searchViewModel.tags.collectAsState().value,
			onTagSelected = { tag -> searchViewModel.onTagSelected(tag) },
			recipes = searchViewModel.recipes.collectAsState().value,
			onRecipeClick = onRecipeClick,
		),
		modifier = modifier,
	)
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun SearchScreen(
	searchUiModel: SearchUiModel,
	modifier: Modifier = Modifier
) {
	Column(
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier
			.padding(start = 16.dp, top = 16.dp, end = 16.dp)
			.fillMaxSize()
	) {
		OutlinedTextField(
			value = searchUiModel.searchValue,
			onValueChange = searchUiModel.onValueChange,
			maxLines = 1,
			singleLine = true,
			shape = MaterialTheme.shapes.small,
			colors = TextFieldDefaults.outlinedTextFieldColors(
				textColor = MaterialTheme.colorScheme.onSurfaceVariant
			),
			placeholder = {
				Text(
					text = stringResource(id = com.team23.presentation.R.string.search_textfield_placeholder),
					color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.69f),
				)
			},
			leadingIcon = {
				Icon(
					imageVector = Icons.Filled.Search,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
				)
			},
			modifier = Modifier.fillMaxWidth(),
		)

		val rowsCount = if (LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE) 1 else 2
		val horizontalItemSpacing = 8.dp
		LazyHorizontalStaggeredGrid(
			rows = StaggeredGridCells.Fixed(count = rowsCount),
			horizontalItemSpacing = horizontalItemSpacing,
			verticalArrangement = Arrangement.spacedBy(8.dp),
			modifier = Modifier.height(32.dp * rowsCount + horizontalItemSpacing)
		) {
			items(searchUiModel.tags) { tag ->
				SearchFilterChip(tag, searchUiModel.onTagSelected)
			}
		}

		LazyVerticalGrid(
			columns = GridCells.Adaptive(300.dp),
			verticalArrangement = Arrangement.spacedBy(16.dp),
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			contentPadding = PaddingValues(bottom = 16.dp),
			modifier = modifier.fillMaxSize()
		) {
			items(searchUiModel.recipes) { recipe ->
				SearchRecipeCard(
					summarizedRecipeUiModel = recipe,
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
				searchValue = "Bretzels",
				onValueChange = { },
				tags = listOf(
					TagUiModel("soup", true),
					TagUiModel("veggie", true),
					TagUiModel("cocktail", false),
					TagUiModel("drink", false),
					TagUiModel("main", false),
					TagUiModel("italian", true)
				),
				onTagSelected = { },
				recipes = List(6) {
					SummarizedRecipeUiModel(
						id = "",
						title = "bretzels",
						imageProperty = NeuracrImageProperty.Resource(
							contentDescription = null,
							imageRes = R.drawable.bretzel
						),
						flagProperty = NeuracrFlagProperty.FRENCH,
					)
				},
				onRecipeClick = {},
			),
		)
	}
}
