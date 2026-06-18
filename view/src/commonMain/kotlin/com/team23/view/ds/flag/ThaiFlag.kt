package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.team23.view.theme.thaiBlue
import com.team23.view.theme.thaiRed
import com.team23.view.theme.thaiWhite

@Composable
internal fun ThaiFlag(
	modifier: Modifier = Modifier,
) {
	Canvas(modifier = modifier) {
		val canvasWidth = size.width
		val canvasHeight = size.height
		drawRect(
			color = thaiBlue(),
			size = Size(width = canvasWidth, height = canvasHeight / 6f),
		)
		drawRect(
			color = thaiWhite(),
			size = Size(width = canvasWidth, height = canvasHeight / 6f),
			topLeft = Offset(x = 0f, y = canvasHeight / 6f),
		)
		drawRect(
			color = thaiRed(),
			size = Size(width = canvasWidth, height = canvasHeight / 3f),
			topLeft = Offset(x = 0f, y = canvasHeight / 3f),
		)
		drawRect(
			color = thaiWhite(),
			size = Size(width = canvasWidth, height = canvasHeight / 6f),
			topLeft = Offset(x = 0f, y = canvasHeight * 2f / 3f),
		)
		drawRect(
			color = thaiBlue(),
			size = Size(width = canvasWidth, height = canvasHeight / 6f),
			topLeft = Offset(x = 0f, y = canvasHeight * 5f / 6f),
		)
	}
}
