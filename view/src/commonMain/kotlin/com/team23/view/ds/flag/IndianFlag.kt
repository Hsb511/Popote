package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.team23.view.theme.indianBlue
import com.team23.view.theme.indianGreen
import com.team23.view.theme.indianSaffron
import com.team23.view.theme.white
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
internal fun IndianFlag(
    modifier: Modifier = Modifier,
) {
    // https://commons.wikimedia.org/wiki/File:Flag_of_India_%28construction_sheet%29_%282-3%29.svg
    Canvas(modifier = modifier) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawRect(
            color = indianSaffron(),
            size = Size(width = canvasWidth, height = canvasHeight / 3f),
        )
        drawRect(
            color = white(),
            size = Size(width = canvasWidth, height = canvasHeight / 3f),
            topLeft = Offset(x = 0f, y = canvasHeight / 3f),
        )
        drawRect(
            color = indianGreen(),
            size = Size(width = canvasWidth, height = canvasHeight / 3f),
            topLeft = Offset(x = 0f, y = canvasHeight * 2f / 3f),
        )
        drawCircle(
            color = indianBlue(),
            radius = canvasHeight * 185f / 600f / 2f,
        )
        drawCircle(
            color = white(),
            radius = canvasHeight * 160f / 600f / 2f,
        )
        val count = 12
        val step = PI.toFloat() / count

        for (i in 0 until count) {
            val angle = i * step
            drawSpike2(
                canvasWidth = canvasWidth,
                canvasHeight = canvasHeight,
                angleRad = angle,
            )
        }
    }
}

private fun DrawScope.drawSpike2(
    canvasWidth: Float,
    canvasHeight: Float,
    angleRad: Float,
) {
    val center = Offset(canvasWidth / 2f, canvasHeight / 2f)

    val halfLength = canvasHeight * 80f / 600f

    val dx = sin(angleRad) * halfLength
    val dy = cos(angleRad) * halfLength

    drawLine(
        color = indianBlue(),
        start = Offset(
            x = center.x - dx,
            y = center.y - dy,
        ),
        end = Offset(
            x = center.x + dx,
            y = center.y + dy,
        ),
        strokeWidth = canvasHeight * 4f / 600f,
    )
}