package com.example.design_system.theming

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val neuracrLightColorScheme = lightColorScheme(
    primary = umber(),
    onPrimary = black(),
    secondary = olivine(),
    onSecondary = white(),
    surface = taupe(),
)

internal val neuracrDarkColorScheme = darkColorScheme(
    primary = black26(),
    onPrimary = white(),
    secondary = black70(),
    onSecondary = cornSilk(),
    surface = black26(),
    onSurface = white(),
    background = black35(),
    onBackground = cornSilk()
)

private fun white() = Color.White
private fun cornSilk() = Color(0xFFFFF8DC)
private fun olivine() = Color(0xFFADC178)
private fun umber() = Color(0xFF6C584C)
private fun taupe() = Color(0xFF533F33)
private fun black70() = Color(0xFF464646)
private fun black35() = Color(0xFF232323)
private fun black26() = Color(0xFF1A1A1A)
private fun black() = Color.Black
