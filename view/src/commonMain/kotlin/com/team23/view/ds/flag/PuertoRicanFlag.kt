package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.team23.view.theme.puertoRicanBlue
import com.team23.view.theme.puertoRicanRed
import com.team23.view.theme.white
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

@Composable
internal fun PuertoRicanFlag(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawPuertoRicanFlag(canvasWidth, canvasHeight)
        }
    }
}

internal fun DrawScope.drawPuertoRicanFlag(canvasWidth: Float, canvasHeight: Float) {
    val puertoRicanStripeHeight = canvasHeight / 5

    drawRect(
        color = white(),
        size = Size(width = canvasWidth, height = canvasHeight),
    )
    for (i in 0..5 step 2) {
        drawRect(
            color = puertoRicanRed(),
            size = Size(width = canvasWidth, height = puertoRicanStripeHeight),
            topLeft = Offset(x = 0f, y = i * puertoRicanStripeHeight),
        )
    }

    drawLeftTriangle(canvasHeight)

    drawFivePointStar(
        center = Offset(x = canvasWidth / 5f, y = canvasHeight / 2f),
        radius = canvasHeight / 6f,
        color = white(),
    )
}

private fun DrawScope.drawLeftTriangle(canvasHeight: Float) {
    val trianglePath = Path().apply {
        moveTo(0f, 0f)
        lineTo(canvasHeight * sqrt(3f) / 2 , canvasHeight / 2)
        lineTo(0f, canvasHeight)
        close()
    }
    drawPath(trianglePath, color = puertoRicanBlue())
}

private fun DrawScope.drawFivePointStar(
    center: Offset,
    radius: Float,
    color: Color,
) {
    val path = Path()

    repeat(10) { i ->
        val r = if (i % 2 == 0) radius else radius * 0.382f
        val angle = -PI.toFloat() / 2f + i * PI.toFloat() / 5f

        val point = Offset(
            x = center.x + cos(angle) * r,
            y = center.y + sin(angle) * r
        )

        if (i == 0) path.moveTo(point.x, point.y)
        else path.lineTo(point.x, point.y)
    }

    path.close()
    drawPath(path, color)
}
