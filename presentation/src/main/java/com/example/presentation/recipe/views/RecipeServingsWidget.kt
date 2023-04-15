package com.example.presentation.recipe.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theming.NeuracrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeServingsWidget(
	servingsAmount: MutableState<Int>,
	defaultServingsAmount: Int,
	modifier: Modifier = Modifier,
) {
	val localFocusManager = LocalFocusManager.current

	OutlinedTextField(
		value = servingsAmount.value.toString(),
		onValueChange = { newValue ->
			servingsAmount.value = newValue.toIntOrNull()?.let {
				if (it > 0) it else 1
			} ?: defaultServingsAmount
		},
		singleLine = true,
		textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
		shape = MaterialTheme.shapes.extraLarge,
		leadingIcon = {
			Text(
				text = "-",
				style = MaterialTheme.typography.titleLarge,
				modifier = Modifier.clickable {
					if (servingsAmount.value > 1) {
						servingsAmount.value--
					}
				},
			)
		},
		trailingIcon = {
			Text(
				text = "+",
				style = MaterialTheme.typography.titleLarge,
				modifier = Modifier.clickable { servingsAmount.value++ },
			)
		},
		keyboardOptions = KeyboardOptions(
			keyboardType = KeyboardType.Number
		),
		keyboardActions = KeyboardActions(onDone = {
			localFocusManager.clearFocus()
		}),
		modifier = modifier
			.width(126.dp)
			.pointerInput(Unit) {
				detectTapGestures(onTap = {
					localFocusManager.clearFocus()
				})
			}
	)
}

@Composable
@Preview(showSystemUi = true)
fun RecipeServingsWidgetPreview() {
	NeuracrTheme {
		val defaultServingsAmount = 4
		val servingsAmount = remember { mutableStateOf(defaultServingsAmount) }
		RecipeServingsWidget(
			servingsAmount = servingsAmount,
			defaultServingsAmount = defaultServingsAmount,
		)
	}
}
