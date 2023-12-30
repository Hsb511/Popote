package com.team23.view.widget.search

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel

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
