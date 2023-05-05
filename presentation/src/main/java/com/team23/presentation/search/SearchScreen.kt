package com.team23.presentation.search

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
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
			colors = TextFieldDefaults.outlinedTextFieldColors(textColor = MaterialTheme.colorScheme.onSurfaceVariant),
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
				FilterChip(
					selected = tag.isSelected,
					onClick = { searchUiModel.onTagSelected(tag) },
					label = {
						Text(
							text = tag.label,
							style = MaterialTheme.typography.bodySmall,
						)
					},
					leadingIcon = if (tag.isSelected) {
						{
							Icon(
								imageVector = Icons.Filled.Check,
								contentDescription = null,
							)
						}
					} else null
				)
			}
		}

		LazyVerticalStaggeredGrid(
			columns = StaggeredGridCells.Adaptive(300.dp),
			verticalItemSpacing = 16.dp,
			horizontalArrangement = Arrangement.spacedBy(16.dp),
			modifier = modifier.fillMaxSize()
		) {
			items(searchUiModel.recipes) { recipe ->
				SearchRecipeCard(recipe, modifier = Modifier.clickable {
					searchUiModel.onRecipeClick(recipe)
				})
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
