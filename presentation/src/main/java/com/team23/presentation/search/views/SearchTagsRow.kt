package com.team23.presentation.search.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.search.models.TagUiModel
import com.team23.presentation.search.models.TagsRowUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchTagsRow(tagsRowUiModel: TagsRowUiModel, modifier: Modifier = Modifier) {
	var isSearchExpanded by remember { mutableStateOf(false) }

	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = modifier.fillMaxWidth(),
	) {
		val selectedTags = tagsRowUiModel.tags.filter { it.isSelected }
		val tagRowSize = 32.dp

		FilledTonalIconButton(
			onClick = { isSearchExpanded = !isSearchExpanded },
			modifier = Modifier
				.padding(horizontal = 8.dp)
				.size(tagRowSize),
		) {
			Icon(
				imageVector = if (isSearchExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
				contentDescription = null,
				tint = MaterialTheme.colorScheme.onBackground,
			)
		}

		if (selectedTags.isEmpty()) {
			Text(
				text = if (isSearchExpanded) {
					stringResource(id = R.string.search_tags_select_tag_indication)
				} else {
					stringResource(id = R.string.search_tags_row_expand_indication)
				},
				style = MaterialTheme.typography.bodyLarge,
				color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.69f),
				modifier = Modifier
					.fillMaxWidth()
					.padding(start = 4.dp)
			)
		}
		LazyHorizontalStaggeredGrid(
			rows = StaggeredGridCells.Fixed(count = 1),
			horizontalItemSpacing = 8.dp,
			verticalArrangement = Arrangement.spacedBy(8.dp),
			modifier = Modifier.height(if (selectedTags.isEmpty()) 0.dp else tagRowSize)
		) {
			items(selectedTags) { tag ->
				SearchFilterChip(tag, tagsRowUiModel.onTagSelected)
			}
		}
	}

	LazyVerticalGrid(
		columns = GridCells.Adaptive(100.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier = if (isSearchExpanded) Modifier else Modifier.height(0.dp)
	) {
		items(tagsRowUiModel.tags) { tag ->
			SearchFilterChip(tag, tagsRowUiModel.onTagSelected)
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun SearchTagsRowPreview() {
	NeuracrTheme {
		SearchTagsRow(
			tagsRowUiModel = TagsRowUiModel(
				tags = listOf(
					TagUiModel("soup", true),
					TagUiModel("veggie", true),
					TagUiModel("cocktail", false),
					TagUiModel("drink", false),
					TagUiModel("main", false),
					TagUiModel("italian", true)
				),
				onTagSelected = { },
			)
		)
	}
}
