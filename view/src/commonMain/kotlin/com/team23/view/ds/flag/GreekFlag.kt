package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.team23.view.theme.greekCyan
import com.team23.view.theme.white

@Composable
internal fun GreekFlag(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawGreekFlag(canvasWidth, canvasHeight)
        }
    }
}

internal fun DrawScope.drawGreekFlag(canvasWidth: Float, canvasHeight: Float) {
    val greekStripeThickness = canvasHeight / 9
    val greekCrossSquareSize = 2 * canvasHeight / 9
    val greekCrossStripeLength = 5 * canvasHeight / 9

    drawRect(
        color = white(),
        size = Size(width = canvasWidth, height = canvasHeight),
    )
    for (i in 0..9 step 2) {
        drawRect(
            color = greekCyan(),
            size = Size(width = canvasWidth, height = greekStripeThickness),
            topLeft = Offset(x = 0f, y = i * greekStripeThickness),
        )
    }

    drawRect(
        color = greekCyan(),
        size = Size(width = greekCrossSquareSize, height = greekCrossSquareSize),
    )
    drawRect(
        color = greekCyan(),
        size = Size(width = greekCrossSquareSize, height = greekCrossSquareSize),
        topLeft = Offset(x = greekCrossSquareSize + greekStripeThickness, y = 0f)
    )
    drawRect(
        color = greekCyan(),
        size = Size(width = greekCrossSquareSize, height = greekCrossSquareSize),
        topLeft = Offset(x = 0f, y = greekCrossSquareSize + greekStripeThickness)
    )
    drawRect(
        color = greekCyan(),
        size = Size(width = greekCrossSquareSize, height = greekCrossSquareSize),
        topLeft = Offset(x = greekCrossSquareSize + greekStripeThickness, y = greekCrossSquareSize + greekStripeThickness)
    )
    drawRect(
        color = white(),
        size = Size(width = greekStripeThickness, height = greekCrossStripeLength),
        topLeft = Offset(x = greekCrossSquareSize, y = 0f)
    )
    drawRect(
        color = white(),
        size = Size(width = greekCrossStripeLength, height = greekStripeThickness),
        topLeft = Offset(x = 0f, y = greekCrossSquareSize)
    )
}
