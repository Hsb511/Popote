package com.team23.presentation.recipe.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun RecipeServingsWidget(
	currentServingsAmount: String,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val localFocusManager = LocalFocusManager.current

	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.height(IntrinsicSize.Min)
			.background(
				color = MaterialTheme.colorScheme.primary,
				shape = MaterialTheme.shapes.extraLarge
			)
	) {
		IconButton(
			onClick = onSubtractOneServing,
			modifier = Modifier.size(36.dp)
		) {
			Text(
				text = "-",
				style = MaterialTheme.typography.titleLarge,
				color = MaterialTheme.colorScheme.onSecondary,
			)
		}
		BasicTextField(
			value = currentServingsAmount,
			onValueChange = { newValue ->
				onValueChanged(newValue)
			},
			singleLine = true,
			textStyle = LocalTextStyle.current.copy(
				color = MaterialTheme.colorScheme.onPrimary,
				textAlign = TextAlign.Center,
			),
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Number
			),
			keyboardActions = KeyboardActions(onDone = {
				localFocusManager.clearFocus()
			}),
			modifier = modifier
				.width(32.dp)
				.pointerInput(Unit) {
					detectTapGestures(onTap = {
						localFocusManager.clearFocus()
					})
				}
		)
		IconButton(
			onClick = onAddOneServing,
			modifier = Modifier.size(36.dp)
		) {
			Text(
				text = "+",
				style = MaterialTheme.typography.titleLarge,
				color = MaterialTheme.colorScheme.onSecondary,
			)
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeServingsWidgetPreview() {
	NeuracrTheme {
		RecipeServingsWidget(
			currentServingsAmount = "4",
			onValueChanged = {},
			onAddOneServing = {},
			onSubtractOneServing = {},
		)
	}
}
