package com.team23.view.widget.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.uimodel.TagsRowUiModel
import com.team23.neuracrsrecipes.model.uimodel.TextFieldUiModel
import com.team23.view.Res
import com.team23.view.extension.BackHandler
import com.team23.view.ic_tag
import com.team23.view.search_tags_row_label
import com.team23.view.search_textfield_placeholder
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchTagsRow(tagsRowUiModel: TagsRowUiModel, modifier: Modifier = Modifier) {
	var searchTagValue by remember { mutableStateOf("") }
	var isMenuExpanded by remember { mutableStateOf(false) }
	val focusManager = LocalFocusManager.current

	BackHandler(enabled = isMenuExpanded) {
		focusManager.clearFocus()
	}

	SearchTextField(
		textFieldUiModel = TextFieldUiModel(
			searchValue = searchTagValue,
			onValueChange = { newValue -> searchTagValue = newValue },
			label = stringResource(Res.string.search_tags_row_label),
			placeholder = stringResource(Res.string.search_textfield_placeholder),
			leadingIcon = IconProperty.Resource(
				drawableResource = Res.drawable.ic_tag,
				contentDescription = "",
				tint = MaterialTheme.colorScheme.onSecondaryContainer,
			),
		),
		modifier = modifier
			.clickable {
				isMenuExpanded = true
			}
			.onFocusChanged { focusState ->
				isMenuExpanded = focusState.hasFocus
			},
	)

	val selectedTags = tagsRowUiModel.tags.filter { it.isSelected }
	LazyRow(
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier = Modifier
			.height(if (selectedTags.isEmpty()) 0.dp else 48.dp)
			.padding(top = 8.dp, bottom = 8.dp)
	) {
		items(selectedTags) { tag ->
			SearchFilterChip(tag, tagsRowUiModel.onTagSelected)
		}
	}

	if (isMenuExpanded) {
		LazyHorizontalStaggeredGrid(
			rows = StaggeredGridCells.Fixed(3),
			horizontalItemSpacing = 8.dp,
			verticalArrangement = Arrangement.spacedBy(8.dp),
			modifier = Modifier
				.height(34.dp * 3 + 8.dp * 2)
				.padding(bottom = 4.dp)
		) {
			items(tagsRowUiModel.tags
				.filter { !it.isSelected }
				.filter { it.label.contains(searchTagValue) }
			) { tag ->
				SearchFilterChip(tag = tag, onTagSelected = tagsRowUiModel.onTagSelected)
			}
		}
	}
}
