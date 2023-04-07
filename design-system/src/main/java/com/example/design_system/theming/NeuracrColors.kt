package com.example.design_system.theming

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val neuracrLightColorScheme = lightColorScheme(
    primary = umber(),
    onPrimary = white(),
    secondary = olivine(),
    onSecondary = white(),
    surface = taupe(),
    onSurface = white(),
    scrim = cornSilk(),
)

internal val neuracrDarkColorScheme = darkColorScheme(
    primary = black26(),
    onPrimary = white(),
    secondary = black70(),
    onSecondary = cornSilk(),
    surface = black13(),
    onSurface = white(),
    background = black35(),
    onBackground = cornSilk(),
    scrim = cornSilk(),
)

private fun white() = Color.White
private fun cornSilk() = Color(0xFFFFF8DC)
private fun olivine() = Color(0xFFADC178)
internal fun umber() = Color(0xFF6C584C)
private fun taupe() = Color(0xFF533F33)
internal fun black95() = Color(0xFFF3F3F3)
internal fun black90() = Color(0xFFE2E2E2)
private fun black70() = Color(0xFF464646)
private fun black35() = Color(0xFF232323)
internal fun black26() = Color(0xFF1A1A1A)
private fun black13() = Color(0xFF0D0D0D)
private fun black() = Color.Black
