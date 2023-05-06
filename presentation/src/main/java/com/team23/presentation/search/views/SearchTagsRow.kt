package com.team23.presentation.search.views

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.search.models.TagUiModel
import com.team23.presentation.search.models.TagsRowUiModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchTagsRow(tagsRowUiModel: TagsRowUiModel, modifier: Modifier = Modifier) {
	val focusManager = LocalFocusManager.current
	val keyboard = LocalSoftwareKeyboardController.current
	var searchTagValue by remember { mutableStateOf("") }
	var isMenuExpanded by remember { mutableStateOf(false) }

	BackHandler(enabled = isMenuExpanded) {
		focusManager.clearFocus()
	}

	OutlinedTextField(
		value = searchTagValue,
		onValueChange = { newValue -> searchTagValue = newValue },
		maxLines = 1,
		singleLine = true,
		shape = MaterialTheme.shapes.small,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			textColor = MaterialTheme.colorScheme.onSurfaceVariant
		),
		label = { Text(text = stringResource(id = R.string.search_tags_row_label)) },
		placeholder = {
			Text(
				text = stringResource(id = R.string.search_textfield_placeholder),
				style = MaterialTheme.typography.bodyLarge,
				color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.69f),
			)
		},
		leadingIcon = {
			Icon(
				painter = painterResource(id = R.drawable.ic_tag),
				contentDescription = null,
				tint = MaterialTheme.colorScheme.primary,
			)
		},
		trailingIcon = {
			if (searchTagValue.isNotEmpty()) {
				Icon(
					imageVector = Icons.Filled.Delete,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
					modifier = Modifier.clickable {
						searchTagValue = ""
					}
				)
			}
		},
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
		modifier = modifier
			.fillMaxWidth()
			.onFocusChanged { focusState ->
				isMenuExpanded = focusState.hasFocus
			},
	)

	val selectedTags = tagsRowUiModel.tags.filter { it.isSelected }
	LazyHorizontalStaggeredGrid(
		rows = StaggeredGridCells.Fixed(count = 1),
		horizontalItemSpacing = 8.dp,
		verticalArrangement = Arrangement.spacedBy(8.dp),
		modifier = Modifier
			.height(if (selectedTags.isEmpty()) 0.dp else 48.dp)
			.padding(top = 8.dp, bottom = 8.dp)
	) {
		items(selectedTags) { tag ->
			SearchFilterChip(tag, tagsRowUiModel.onTagSelected)
		}
	}
	LazyVerticalGrid(
		columns = GridCells.Adaptive(100.dp),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier = if (isMenuExpanded) Modifier.offset(y = -(8.dp)) else Modifier.height(0.dp)
	) {
		items(tagsRowUiModel.tags
			.filter { !it.isSelected }
			.filter { it.label.contains(searchTagValue) }
		) { tag ->
			SearchFilterChip(tag = tag, onTagSelected = tagsRowUiModel.onTagSelected)
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
