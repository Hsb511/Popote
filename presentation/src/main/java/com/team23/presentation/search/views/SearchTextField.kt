package com.team23.presentation.search.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.search.models.TextFieldUiModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchTextField(textFieldUiModel: TextFieldUiModel, modifier: Modifier = Modifier) {
	OutlinedTextField(
		value = textFieldUiModel.searchValue,
		onValueChange = textFieldUiModel.onValueChange,
		maxLines = 1,
		singleLine = true,
		shape = MaterialTheme.shapes.small,
		colors = TextFieldDefaults.outlinedTextFieldColors(
			textColor = MaterialTheme.colorScheme.onSurfaceVariant
		),
		label = { Text(text = stringResource(id = R.string.search_textfield_label)) },
		placeholder = {
			Text(
				text = stringResource(id = R.string.search_textfield_placeholder),
				style = MaterialTheme.typography.bodyLarge,
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
		trailingIcon = {
			if (textFieldUiModel.searchValue.isNotEmpty()) {
				Icon(
					imageVector = Icons.Filled.Delete,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
					modifier = Modifier.clickable {
						textFieldUiModel.onValueChange("")
					}
				)
			}
		},
		modifier = modifier.fillMaxWidth(),
	)
}

@Composable
@Preview(showSystemUi = true)
fun SearchTextFieldPreview() {
	NeuracrTheme {
		SearchTextField(
			textFieldUiModel = TextFieldUiModel(
				searchValue = "Bretzels",
				onValueChange = { },
			),
		)
	}
}
