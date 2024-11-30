package com.team23.view.ds.display

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke

@Composable
fun DisplayList(
	tint: Color,
	modifier: Modifier = Modifier
) {
	Canvas(modifier = modifier.aspectRatio(1f)) {
		val canvasWidth = size.width
		val canvasHeight = size.height
		drawRoundRect(
			color = tint,
			topLeft = Offset(x = canvasWidth / 10, y = canvasHeight / 10),
			size = Size(width = canvasWidth * 8 / 10, height = canvasHeight * 1.9f / 10),
			cornerRadius = CornerRadius(x = canvasWidth / 20, y = canvasHeight / 20),
			style = Stroke(width = canvasWidth / 15),
		)
		drawRoundRect(
			color = tint,
			topLeft = Offset(x = canvasWidth / 10, y = canvasHeight * 4.1f / 10),
			size = Size(width = canvasWidth * 8 / 10, height = canvasHeight * 1.9f / 10),
			cornerRadius = CornerRadius(x = canvasWidth / 20, y = canvasHeight / 20),
			style = Stroke(width = canvasWidth / 15),
		)
		drawRoundRect(
			color = tint,
			topLeft = Offset(x = canvasWidth / 10, y = canvasHeight * 7.2f / 10),
			size = Size(width = canvasWidth * 8 / 10, height = canvasHeight * 1.9f / 10),
			cornerRadius = CornerRadius(x = canvasWidth / 20, y = canvasHeight / 20),
			style = Stroke(width = canvasWidth / 15),
		)
	}
}
