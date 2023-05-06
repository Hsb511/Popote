package com.team23.presentation.search.views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.search.models.TagUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchFilterChip(tag: TagUiModel, onTagSelected: (TagUiModel) -> Unit) {
	FilterChip(
		selected = tag.isSelected,
		onClick = { onTagSelected(tag) },
		label = {
			Text(
				text = tag.label,
				style = MaterialTheme.typography.bodySmall,
			)
		},
		trailingIcon = if (tag.isSelected) {
			{
				Icon(
					imageVector = Icons.Filled.Close,
					contentDescription = null,
				)
			}
		} else null
	)
}

@Composable
@Preview(showBackground = true)
fun SearchFilterChipPreview() {
	NeuracrTheme {
		SearchFilterChip(
			TagUiModel("soup", true)
		) {}
	}
}
