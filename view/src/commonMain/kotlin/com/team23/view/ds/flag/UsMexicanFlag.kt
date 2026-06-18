package com.team23.view.ds.flag

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.team23.view.Res
import com.team23.view.flag_mexican
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun UsMexicanFlag(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            imageVector = vectorResource(Res.drawable.flag_mexican),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
        Canvas(modifier = Modifier.fillMaxSize().clip(shape = GenericShape { size, _ ->
            moveTo(0f, 0f)
            lineTo(size.width, 0f)
            lineTo(0f, size.height)
        })) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawUsFlag(canvasWidth, canvasHeight)
        }
    }
}
