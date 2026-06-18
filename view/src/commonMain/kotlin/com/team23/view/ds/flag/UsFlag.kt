package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.team23.view.theme.usBlue
import com.team23.view.theme.usRed
import com.team23.view.theme.white

@Composable
internal fun UsFlag(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawUsFlag(canvasWidth, canvasHeight)
        }
    }
}

internal fun DrawScope.drawUsFlag(canvasWidth: Float, canvasHeight: Float) {
    val usStripeHeight = canvasHeight / 13
    val usUnionWidth = 2 * canvasWidth / 5
    val usUnionHeight = 7 * canvasHeight / 13
    drawRect(
        color = white(),
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
        color = white(),
        radius = starSize / 2,
        center = center,
    )
}
