package com.team23.presentation.search.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.search.SearchSamples.previewTextFieldSample
import com.team23.presentation.search.models.TextFieldUiModel

@Composable
fun SearchTextField(
	textFieldUiModel: TextFieldUiModel,
	modifier: Modifier = Modifier,
) {
	OutlinedTextField(
		value = textFieldUiModel.searchValue,
		onValueChange = textFieldUiModel.onValueChange,
		maxLines = 1,
		singleLine = true,
		shape = MaterialTheme.shapes.small,
		interactionSource = textFieldUiModel.interactionSource,
		label = { Text(text = stringResource(id = textFieldUiModel.label)) },
		placeholder = {
			Text(
				text = stringResource(id = textFieldUiModel.placeholder),
				style = MaterialTheme.typography.bodyLarge,
				color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.69f),
			)
		},
		leadingIcon = {
			when (val icon = textFieldUiModel.leadingIcon) {
				is TextFieldUiModel.IconUiModel.Painter -> Icon(
					painter = painterResource(id = icon.resId),
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
				)

				is TextFieldUiModel.IconUiModel.Vector -> Icon(
					imageVector = icon.image,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.primary,
				)
			}
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
@Preview(showBackground = true)
fun SearchTextFieldPreview() {
	NeuracrTheme {
		SearchTextField(
			textFieldUiModel = previewTextFieldSample,
			modifier = Modifier.background(Color.White),
		)
	}
}
