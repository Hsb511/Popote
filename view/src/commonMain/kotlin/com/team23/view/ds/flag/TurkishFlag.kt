package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.team23.view.theme.turkishRed
import com.team23.view.theme.white
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun TurkishFlag(
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val flagHeight = size.height
        val flagWidth = size.width

        drawRect(
            color = turkishRed(),
            size = Size(flagWidth, flagHeight)
        )

        val g = flagHeight

        val outerCrescentCenter = Offset(x = g * 0.5f, y = g * 0.5f)
        val innerCrescentCenter = Offset(x = outerCrescentCenter.x + g * (1f / 16f), y = outerCrescentCenter.y)

        drawCircle(
            color = Color.White,
            radius = g * 0.25f,
            center = outerCrescentCenter
        )

        drawCircle(
            color = turkishRed(),
            radius = g * 0.2f,
            center = innerCrescentCenter
        )

        val starRadius = g * 0.125f
        val starCenter = Offset(x = innerCrescentCenter.x + g * (1f / 3f) + starRadius, y = g * 0.5f)

        drawFivePointStar(
            center = starCenter,
            radius = starRadius,
            color = white(),
        )
    }
}

private fun DrawScope.drawFivePointStar(
    center: Offset,
    radius: Float,
    color: Color,
    rotationRad: Float = -PI.toFloat() / 2f,
) {
    val path = Path()

    repeat(10) { i ->
        val r = if (i % 2 == 0) radius else radius * 0.382f
        val angle = rotationRad + i * PI.toFloat() / 5f

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
