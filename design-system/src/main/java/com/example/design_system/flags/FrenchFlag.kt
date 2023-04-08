package com.example.design_system.flags

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.theming.frenchBlue
import com.example.design_system.theming.frenchRed
import com.example.design_system.theming.frenchWhite

@Composable
internal fun FrenchFlag(
	modifier: Modifier = Modifier,
) {
	Canvas(modifier = modifier) {
		val canvasWidth = size.width
		val canvasHeight = size.height
		drawRect(
			color = frenchBlue(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
		)
		drawRect(
			color = frenchWhite(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
			topLeft = Offset(x = canvasWidth / 3, y = 0f)
		)
		drawRect(
			color = frenchRed(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
			topLeft = Offset(x = 2 * canvasWidth / 3, y = 0f)
		)
	}
}

@Composable
@Preview(showBackground = true)
private fun FrenchFlagPreview() {
	FrenchFlag(
		modifier = Modifier
			.width(300.dp)
			.height(200.dp)
	)
}
