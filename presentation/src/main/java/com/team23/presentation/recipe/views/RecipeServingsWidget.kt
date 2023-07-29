package com.team23.presentation.recipe.views

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R

@Composable
fun RecipeServingsWidget(
	currentServingsAmount: String,
	onValueChanged: (String) -> Unit,
	onAddOneServing: () -> Unit,
	onSubtractOneServing: () -> Unit,
	widgetWidth: MutableState<Int>,
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
			.onGloballyPositioned { coordinates ->
				widgetWidth.value = coordinates.size.width
			}
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
				textAlign = TextAlign.End,
			),
			keyboardOptions = KeyboardOptions(
				keyboardType = KeyboardType.Number
			),
			keyboardActions = KeyboardActions(onDone = {
				localFocusManager.clearFocus()
			}),
			modifier = modifier
				.width(
					currentServingsAmount
						.toIntOrNull()
						?.let { servings ->
							when {
								servings < 10 -> 16.dp
								servings < 100 -> 24.dp
								else -> 32.dp
							}
						} ?: 32.dp
				)
				.pointerInput(Unit) {
					detectTapGestures(onTap = {
						localFocusManager.clearFocus()
					})
				}
		)
		Text(
			text = stringResource(id = R.string.recipe_number_of_servings),
			modifier = Modifier.padding(horizontal = 8.dp)
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
@Preview(showBackground = true)
fun RecipeServingsWidgetPreview() {
	NeuracrTheme {
		RecipeServingsWidget(
			currentServingsAmount = "4",
			onValueChanged = {},
			onAddOneServing = {},
			onSubtractOneServing = {},
			widgetWidth = remember { mutableStateOf(100) }
		)
	}
}
