package com.team23.view.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font
import com.team23.view.Res
import kotlinx.coroutines.runBlocking
import org.jetbrains.compose.resources.ExperimentalResourceApi

private val cache: MutableMap<String, Font> = mutableMapOf()

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun font(
    resId: String,
    weight: FontWeight
) = cache.getOrPut(resId) {
    val byteArray = runBlocking {
        Res.readBytes("font/$resId.ttf")
    }
    Font(resId, byteArray, weight)
}
