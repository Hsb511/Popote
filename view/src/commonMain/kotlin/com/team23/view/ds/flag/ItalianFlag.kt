package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.team23.view.theme.italianGreen
import com.team23.view.theme.italianRed
import com.team23.view.theme.italianWhite

@Composable
internal fun ItalianFlag(
	modifier: Modifier = Modifier,
) {
	Canvas(modifier = modifier) {
		val canvasWidth = size.width
		val canvasHeight = size.height
		drawRect(
			color = italianGreen(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
		)
		drawRect(
			color = italianWhite(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
			topLeft = Offset(x = canvasWidth / 3, y = 0f)
		)
		drawRect(
			color = italianRed(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
			topLeft = Offset(x = 2 * canvasWidth / 3, y = 0f)
		)
	}
}
