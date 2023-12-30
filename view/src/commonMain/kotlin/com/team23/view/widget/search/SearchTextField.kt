package com.team23.view.widget.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import com.team23.neuracrsrecipes.model.uimodel.TextFieldUiModel
import com.team23.view.ds.icon.NeuracrIcon

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTextField(
	textFieldUiModel: TextFieldUiModel,
	modifier: Modifier = Modifier,
) {
	val focusManager = LocalFocusManager.current
	val keyboard = LocalSoftwareKeyboardController.current

	OutlinedTextField(
		value = textFieldUiModel.searchValue,
		onValueChange = textFieldUiModel.onValueChange,
		maxLines = 1,
		singleLine = true,
		shape = MaterialTheme.shapes.small,
		label = { Text(text = textFieldUiModel.label) },
		keyboardActions = KeyboardActions(
			onDone = {
				keyboard?.hide()
				focusManager.clearFocus()
				textFieldUiModel.onValueChange("")
			},
			onGo = {
				keyboard?.hide()
				focusManager.clearFocus()
				textFieldUiModel.onValueChange("")
			}
		),
		placeholder = {
			Text(
				text = textFieldUiModel.placeholder,
				style = MaterialTheme.typography.bodyLarge,
				color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.69f),
			)
		},
		leadingIcon = {
			NeuracrIcon(textFieldUiModel.leadingIcon)
		},
		trailingIcon = {
			if (textFieldUiModel.searchValue.isNotEmpty()) {
				Icon(
					imageVector = Icons.Filled.Delete,
					contentDescription = null,
					tint = MaterialTheme.colorScheme.onSecondaryContainer,
					modifier = Modifier.clickable {
						textFieldUiModel.onValueChange("")
					}
				)
			}
		},
		modifier = modifier.fillMaxWidth(),
	)
}
