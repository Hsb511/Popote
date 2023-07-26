package com.team23.presentation.add.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun AddTextField(
	initialText: String?,
	onTextChange: (String) -> Unit,
	style: TextStyle,
	placeholder: String,
	modifier: Modifier = Modifier,
) {
	var text by remember { mutableStateOf(initialText) }
	val textHorizontalPadding = 4.dp
	BasicTextField(
		value = text ?: "",
		onValueChange = { value ->
			text = value
			onTextChange(value)
		},
		textStyle = style,
		cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
		decorationBox = { innerTextField ->
			Box(modifier = Modifier.padding(horizontal = textHorizontalPadding)) {
				if (text.isNullOrEmpty()) {
					Text(
						text = placeholder,
						style = style,
						color = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.5f),
					)
				}
				innerTextField()
			}
		},
		modifier = modifier
			.clip(shape = MaterialTheme.shapes.medium)
			.border(
				border = if (text.isNullOrBlank()) {
					BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary)
				} else {
					BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f))
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
			initialText = "value",
			onTextChange = {},
			placeholder = "value",
			style = MaterialTheme.typography.displayLarge,
		)
	}
}
