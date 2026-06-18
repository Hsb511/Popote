package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import com.team23.view.theme.hungarianGreen
import com.team23.view.theme.hungarianRed
import com.team23.view.theme.white

@Composable
internal fun HungarianFlag(
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawRect(
            color = hungarianRed(),
            size = Size(width = canvasWidth, height = canvasHeight / 3f),
        )
        drawRect(
            color = white(),
            size = Size(width = canvasWidth, height = canvasHeight / 3f),
            topLeft = Offset(x = 0f, y = canvasHeight / 3f),
        )
        drawRect(
            color = hungarianGreen(),
            size = Size(width = canvasWidth, height = canvasHeight / 3f),
            topLeft = Offset(x = 0f, y = canvasHeight * 2f / 3f),
        )
    }
}
