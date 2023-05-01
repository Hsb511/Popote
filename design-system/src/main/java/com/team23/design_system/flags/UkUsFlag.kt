package com.team23.design_system.flags

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.*

@Composable
internal fun UkUsFlag(modifier: Modifier = Modifier) {
	Box(modifier = modifier) {
		Canvas(modifier = Modifier.fillMaxSize()) {
			val canvasWidth = size.width
			val canvasHeight = size.height
			drawUsFlag(canvasWidth, canvasHeight)
		}
		Canvas(modifier = Modifier
			.clip(shape = GenericShape {size, _ ->
				moveTo(size.width, 0f)
				lineTo(size.width, size.height)
				lineTo(0f, size.height)
			})
			.fillMaxSize()) {
			val canvasWidth = size.width
			val canvasHeight = size.height
			drawUkFlag(canvasWidth, canvasHeight)
		}
	}
}

private fun DrawScope.drawUsFlag(canvasWidth: Float, canvasHeight: Float) {
	val usStripeHeight = canvasHeight / 13
	val usUnionWidth = 2 * canvasWidth / 5
	val usUnionHeight = 7 * canvasHeight / 13
	drawRect(
		color = ukUsWhite(),
		size = Size(width = canvasWidth, height = canvasHeight),
	)
	for (i in 0..13 step 2) {
		drawRect(
			color = usRed(),
			size = Size(width = canvasWidth, height = usStripeHeight),
			topLeft = Offset(x = 0f, y = i * usStripeHeight),
		)
	}
	drawRect(
		color = usBlue(),
		size = Size(width = usUnionWidth, height = usUnionHeight),
	)
	for (row in 1..5) {
		for (col in 1..6) {
			usStar(
				canvasHeight = canvasHeight,
				center = Offset(
					x = col * usUnionWidth / 6 - usUnionWidth / 12,
					y = row * usUnionHeight / 5 - usUnionHeight / 10,
				),
			)
		}
	}
	for (row in 1..4) {
		for (col in 1..5) {
			usStar(
				canvasHeight = canvasHeight,
				center = Offset(
					x = col * usUnionWidth / 5 - col * usUnionWidth / 30,
					y = row * usUnionHeight / 4 - row * usUnionHeight / 20,
				),
			)
		}
	}
}

private fun DrawScope.usStar(canvasHeight: Float, center: Offset) {
	val starSize = canvasHeight / 24
	drawCircle(
		color = ukUsWhite(),
		radius = starSize / 2,
		center = center,
	)
}

private fun DrawScope.drawUkFlag(canvasWidth: Float, canvasHeight: Float) {
	drawRect(
		color = ukBlue(),
		size = Size(width = canvasWidth, height = canvasHeight),
	)
	drawLine(
		color = ukUsWhite(),
		start = Offset(x = 0f, y = 0f),
		end = Offset(x = canvasWidth, y = canvasHeight),
		strokeWidth = 6 * canvasHeight / 30,
	)
	drawLine(
		color = ukUsWhite(),
		start = Offset(x = canvasWidth, y = 0f),
		end = Offset(x = 0f, y = canvasHeight),
		strokeWidth = 6 * canvasHeight / 30,
	)
	drawLine(
		color = ukRed(),
		start = Offset(x = -2 * canvasHeight / 30, y = 0f),
		end = Offset(x = canvasWidth / 2 - 2 * canvasHeight / 30, y = canvasHeight / 2),
		strokeWidth = 2 * canvasHeight / 30,
	)
	drawLine(
		color = ukRed(),
		start = Offset(x = 0f, y = canvasHeight + canvasHeight / 30),
		end = Offset(x = canvasWidth / 2 + 2 * canvasHeight / 30, y = canvasHeight / 2),
		strokeWidth = 2 * canvasHeight / 30,
	)
	drawLine(
		color = ukRed(),
		start = Offset(x = canvasWidth, y = -canvasHeight / 30),
		end = Offset(x = canvasWidth / 2 - 2 * canvasHeight / 30, y = canvasHeight / 2),
		strokeWidth = 2 * canvasHeight / 30,
	)
	drawLine(
		color = ukRed(),
		start = Offset(x = canvasWidth + 2 * canvasHeight / 30, y = canvasHeight),
		end = Offset(x = canvasWidth / 2 + 2 * canvasHeight / 30, y = canvasHeight / 2),
		strokeWidth = 2 * canvasHeight / 30,
	)
	drawRect(
		color = ukUsWhite(),
		size = Size(width = 10 * canvasWidth / 50, height = canvasHeight),
		topLeft = Offset(x = 20 * canvasWidth / 50, y = 0f)
	)
	drawLine(
		color = ukRed(),
		start = Offset(x = 0f, y = canvasHeight + canvasHeight / 30),
		end = Offset(x = canvasWidth / 2 + 2 * canvasHeight / 30, y = canvasHeight / 2),
		strokeWidth = 2 * canvasHeight / 30,
	)
	drawRect(
		color = ukUsWhite(),
		size = Size(width = canvasWidth, height = 10 * canvasHeight / 30),
		topLeft = Offset(x = 0f, y = 10 * canvasHeight / 30)
	)
	drawRect(
		color = ukRed(),
		size = Size(width = 6 * canvasWidth / 50, height = canvasHeight),
		topLeft = Offset(x = 22 * canvasWidth / 50, y = 0f)
	)
	drawRect(
		color = ukRed(),
		size = Size(width = canvasWidth, height = 6 * canvasHeight / 30),
		topLeft = Offset(x = 0f, y = 12 * canvasHeight / 30)
	)
}

@Composable
@Preview(showBackground = true)
private fun UkUsaFlagPreview() {
	UkUsFlag(modifier = Modifier
		.width(300.dp)
		.height(200.dp))
}