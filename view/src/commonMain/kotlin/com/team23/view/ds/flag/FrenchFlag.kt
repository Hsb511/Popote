package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.team23.view.theme.frenchBlue
import com.team23.view.theme.frenchRed
import com.team23.view.theme.frenchWhite

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
