package com.example.design_system.theming

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val neuracrLightColorScheme = lightColorScheme(
    primary = umber(),
    onPrimary = white(),
	primaryContainer = cornSilk(),
    secondary = olivine(),
    onSecondary = white(),
    surface = taupe(),
    onSurface = white(),
	onSurfaceVariant = cornSilk(),
	inverseSurface = black90(),
	inverseOnSurface = taupe(),
	outline = black(),
	outlineVariant = black90(),
    scrim = umber(),
)

internal val neuracrDarkColorScheme = darkColorScheme(
    primary = black26(),
    onPrimary = white(),
	primaryContainer = black26(),
    secondary = black50(),
    onSecondary = white(),
	onSurfaceVariant = cornSilk(),
    surface = black13(),
    onSurface = white(),
	inverseSurface = black35(),
	inverseOnSurface = white(),
    background = black35(),
    onBackground = cornSilk(),
	outline = white(),
	outlineVariant = black70(),
    scrim = black90(),
)

private fun white() = Color.White
private fun cornSilk() = Color(0xFFFFF8DC)
private fun olivine() = Color(0xFFADC178)
internal fun umber() = Color(0xFF6C584C)
private fun taupe() = Color(0xFF533F33)
internal fun black95() = Color(0xFFF3F3F3)
internal fun black90() = Color(0xFFE2E2E2)

internal fun black70() = Color(0xFF999999)
private fun black50() = Color(0xFF464646)
internal fun black35() = Color(0xFF232323)
internal fun black26() = Color(0xFF1A1A1A)
private fun black13() = Color(0xFF0D0D0D)
private fun black() = Color.Black

internal fun frenchBlue() = Color(0xFF0050A4)
internal fun frenchWhite() = Color(0xFFFFFFFF)
internal fun frenchRed() = Color(0xFFEF4135)

internal fun ukBlue() = Color(0xFF00247D)
internal fun ukRed() = Color(0xFFCF142B)
internal fun ukUsWhite()= Color(0xFFFFFFFF)
internal fun usBlue() = Color(0xFF3C3B6E)
internal fun usRed() = Color(0xFFB22234)
