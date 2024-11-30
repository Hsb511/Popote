package com.team23.view.ds.icon

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FillSearchIcon(
	tint: Color,
	modifier: Modifier = Modifier
) {
	Box(modifier = Modifier.size(24.dp)) {
		Icon(
			imageVector = Icons.Filled.Search,
			contentDescription = null,
			tint = tint,
		)
		Canvas(modifier = modifier.aspectRatio(1f)) {
			val canvasSize = size.width
			drawCircle(
				color = tint,
				radius = canvasSize / 7f,
				center = Offset(x = canvasSize / 2.53f, y = canvasSize / 2.53f),
			)
		}
	}
}
