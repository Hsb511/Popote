package com.team23.presentation.add.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun AddTextField(
	text: String?,
	onTextChange: (String) -> Unit,
	style: TextStyle,
	placeholder: String,
	singleLine: Boolean,
	modifier: Modifier = Modifier,
	keyboardType: KeyboardType = KeyboardType.Text,
) {
	val textHorizontalPadding = 4.dp
	val textFieldColor = MaterialTheme.colorScheme.onSecondaryContainer
	BasicTextField(
		value = text ?: "",
		onValueChange = { value ->
			onTextChange(value)
		},
		textStyle = style.copy(color = textFieldColor),
		cursorBrush = SolidColor(textFieldColor),
		singleLine = singleLine,
		keyboardOptions = if (singleLine) {
			KeyboardOptions(imeAction = ImeAction.Next)
		} else {
			KeyboardOptions.Default
		}.copy(keyboardType = keyboardType),
		decorationBox = { innerTextField ->
			Box(modifier = Modifier.padding(horizontal = textHorizontalPadding)) {
				if (text.isNullOrEmpty()) {
					Text(
						text = placeholder,
						style = style,
						color = textFieldColor.copy(alpha = 0.5f),
					)
				}
				innerTextField()
			}
		},
		modifier = modifier
			.clip(shape = MaterialTheme.shapes.medium)
			.border(
				border = if (text.isNullOrBlank()) {
					BorderStroke(width = 2.dp, color = textFieldColor)
				} else {
					BorderStroke(width = 1.dp, color = textFieldColor.copy(alpha = 0.5f))
				},
				shape = MaterialTheme.shapes.medium,
			)
			.padding(horizontal = textHorizontalPadding),
	)
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun AddTextFieldPreview() {
	NeuracrTheme {
		AddTextField(
			text = "value",
			onTextChange = {},
			placeholder = "value",
			style = MaterialTheme.typography.displayLarge,
			singleLine = false,
		)
	}
}
