package com.team23.view.theme

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight

@SuppressLint("DiscouragedApi")
@Composable
actual fun font(
    resId: String,
    weight: FontWeight
): Font {
    val context = LocalContext.current
    val id = context.resources.getIdentifier(resId, "font", context.packageName)
    return Font(id, weight)
}
