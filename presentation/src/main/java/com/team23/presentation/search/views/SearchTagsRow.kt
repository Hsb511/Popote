package com.team23.presentation.search.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.search.models.TagUiModel
import com.team23.presentation.search.models.TagsRowUiModel
import com.team23.presentation.search.models.TextFieldUiModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchTagsRow(tagsRowUiModel: TagsRowUiModel, modifier: Modifier = Modifier) {
	val focusManager = LocalFocusManager.current
	val keyboard = LocalSoftwareKeyboardController.current
	val interactionSource = remember { MutableInteractionSource() }
	var searchTagValue by remember { mutableStateOf("") }
	var isMenuExpanded by remember { mutableStateOf(false) }

	BackHandler(enabled = isMenuExpanded) {
		focusManager.clearFocus()
	}

	SearchTextField(
		textFieldUiModel = TextFieldUiModel(
			searchValue = searchTagValue,
			onValueChange = { newValue -> searchTagValue = newValue },
			interactionSource = interactionSource,
			label = R.string.search_tags_row_label,
			placeholder = R.string.search_textfield_placeholder,
			leadingIcon = TextFieldUiModel.IconUiModel.Painter(R.drawable.ic_tag),
			keyboardActions = KeyboardActions(
				onDone = {
					keyboard?.hide()
					focusManager.clearFocus()
					searchTagValue = ""
				},
				onGo = {
					keyboard?.hide()
					focusManager.clearFocus()
					searchTagValue = ""
				}
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
