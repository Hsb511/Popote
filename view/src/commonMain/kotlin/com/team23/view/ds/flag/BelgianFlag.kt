package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.team23.view.theme.belgianBlack
import com.team23.view.theme.belgianRed
import com.team23.view.theme.belgianYellow
import com.team23.view.theme.frenchBlue
import com.team23.view.theme.frenchRed
import com.team23.view.theme.white

@Composable
internal fun BelgianFlag(
	modifier: Modifier = Modifier,
) {
	Canvas(modifier = modifier) {
		val canvasWidth = size.width
		val canvasHeight = size.height
		drawRect(
			color = belgianBlack(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
		)
		drawRect(
			color = belgianYellow(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
			topLeft = Offset(x = canvasWidth / 3, y = 0f)
		)
		drawRect(
			color = belgianRed(),
			size = Size(width = canvasWidth / 3, height = canvasHeight),
			topLeft = Offset(x = 2 * canvasWidth / 3, y = 0f)
		)
	}
}
