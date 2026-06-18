package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.team23.view.theme.chineseRed
import com.team23.view.theme.chineseYellow
import com.team23.view.theme.hungarianGreen
import com.team23.view.theme.hungarianRed
import com.team23.view.theme.white
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun ChineseFlag(
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawRect(
            color = chineseRed(),
            size = Size(canvasWidth, canvasHeight)
        )
        val bigStarCenter = Offset(x = canvasWidth * 0.15f, y = canvasHeight * 0.25f)
        drawStar(
            center = bigStarCenter,
            radius = canvasHeight * 0.15f,
        )

        val smallStarRadius = canvasHeight * 0.05f

        val smallCenters = listOf(
            Offset(canvasWidth * 0.30f, canvasHeight * 0.10f),
            Offset(canvasWidth * 0.35f, canvasHeight * 0.20f),
            Offset(canvasWidth * 0.35f, canvasHeight * 0.35f),
            Offset(canvasWidth * 0.30f, canvasHeight * 0.45f),
        )

        smallCenters.forEach { center ->
            drawStar(
                center = center,
                radius = smallStarRadius,
                rotationRad = angleTowards(center, bigStarCenter)
            )
        }
    }
}

fun DrawScope.drawStar(
    center: Offset,
    radius: Float,
    rotationRad: Float = -PI.toFloat() / 2f
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
    drawPath(path, chineseYellow())
}

fun angleTowards(from: Offset, to: Offset): Float {
    return kotlin.math.atan2(to.y - from.y, to.x - from.x) - PI.toFloat() / 2f
}
