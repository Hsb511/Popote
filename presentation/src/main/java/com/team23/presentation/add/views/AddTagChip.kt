package com.team23.presentation.add.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun AddTagChip(
	tags: MutableState<List<String>>,
	allTags: List<String>,
	onAddTag: (String) -> Unit,
) {
	val expanded = remember { mutableStateOf(false) }

	OutlinedButton(
		colors = ButtonDefaults.outlinedButtonColors(
			contentColor = MaterialTheme.colorScheme.tertiary,
		),
		border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
		shape = MaterialTheme.shapes.medium,
		onClick = {
			expanded.value = true
		},
		modifier = Modifier.width(80.dp)
	) {
		Icon(
			imageVector = Icons.Filled.Add,
			contentDescription = "Add",
		)
	}
	TagMenu(
		allTags = allTags,
		onAddTag = { newTag ->
			onAddTag(newTag)
			tags.value = tags.value + newTag
		},
		expanded = expanded,
	)
}

@Composable
@Preview(showBackground = true)
fun AddTagChipPreview() {
	NeuracrTheme {
		AddTagChip(tags = remember { mutableStateOf(emptyList()) }, allTags = listOf(), onAddTag = {})
	}
}

@Composable
private fun TagMenu(
	allTags: List<String>,
	onAddTag: (String) -> Unit,
	expanded: MutableState<Boolean>,
) {
	DropdownMenu(
		expanded = expanded.value,
		onDismissRequest = { expanded.value = false }
	) {
		allTags.forEach { tag ->
			DropdownMenuItem(
				text = { Text(tag) },
				onClick = { onAddTag(tag) }
			)
		}
	}
}