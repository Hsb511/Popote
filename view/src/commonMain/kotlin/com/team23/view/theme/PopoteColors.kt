package com.team23.view.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

internal val popoteLightColorScheme = lightColorScheme(
	primary = umber(),
	onPrimary = white(),
	primaryContainer = melon(),
	onPrimaryContainer = black13(),
	inversePrimary = olivine(),
	secondary = olivine(),
	secondaryContainer = olivine(),
	onSecondary = white(),
	onSecondaryContainer = umber(),
	tertiary = taupe(),
	onTertiary = white(),
	surface = black90(),
	onSurface = taupe(),
	surfaceVariant = black95(),
	onSurfaceVariant = taupe(),
	inverseSurface = black26(),
	inverseOnSurface = white(),
	error = ukRed(),
	outline = black(),
	outlineVariant = black90(),
	scrim = umber(),
)

internal val popoteDarkColorScheme = darkColorScheme(
	primary = black26(),
	onPrimary = white(),
	primaryContainer = olivine(),
	onPrimaryContainer = black13(),
	inversePrimary = taupe(),
	secondary = resedaGreen(),
	onSecondary = white(),
	onSecondaryContainer = cornSilk(),
	tertiary = black13(),
	onTertiary = white(),
	surface = black35(),
	onSurface = white(),
	surfaceVariant = black13(),
	onSurfaceVariant = cornSilk(),
	inverseSurface = black90(),
	inverseOnSurface = black(),
	error = ukRed(),
	background = black35(),
	onBackground = cornSilk(),
	outline = white(),
	outlineVariant = black70(),
	scrim = black90(),
)

internal fun white() = Color.White
private fun cornSilk() = Color(0xFFFFF8DC)
private fun olivine() = Color(0xFFADC178)
private fun resedaGreen() = Color(0xFF464B39)
private fun melon() = Color(0xFFEFB0A1)
fun umber() = Color(0xFF6C584C)
private fun taupe() = Color(0xFF533F33)
internal fun black95() = Color(0xFFF3F3F3)
fun black90() = Color(0xFFE2E2E2)

internal fun black70() = Color(0xFF999999)
fun black35() = Color(0xFF232323)
fun black26() = Color(0xFF1A1A1A)
private fun black13() = Color(0xFF0D0D0D)
private fun black() = Color.Black

internal fun chineseRed() = Color(0xFFEE1C25) // https://en.wikipedia.org/wiki/Flag_of_China
internal fun chineseYellow() = Color(0xFFFFFF00) // https://en.wikipedia.org/wiki/Flag_of_China

internal fun frenchBlue() = Color(0xFF000091) // https://www.info.gouv.fr/marque-de-letat/les-couleurs
internal fun frenchRed() = Color(0xFFE1000F) // https://www.info.gouv.fr/marque-de-letat/les-couleurs

internal fun hungarianRed() = Color(0xFFCE2939) // https://en.wikipedia.org/wiki/Flag_of_Hungary
internal fun hungarianGreen() = Color(0xFF477050) // https://en.wikipedia.org/wiki/Flag_of_Hungary

internal fun indianSaffron() = Color(0xFFFF671F) // https://en.wikipedia.org/wiki/Flag_of_India#Colours
internal fun indianGreen() = Color(0xFF046A38) // https://en.wikipedia.org/wiki/Flag_of_India#Colours
internal fun indianBlue() = Color(0xFF06038D) // https://en.wikipedia.org/wiki/Flag_of_India#Colours

internal fun italianGreen() = Color(0xFF009246) // https://radiomarconi1895.altervista.org/marconi/dpcm_14-06.html
internal fun italianWhite() = Color(0xFFF4F5F0) // https://radiomarconi1895.altervista.org/marconi/dpcm_14-06.html
internal fun italianRed() = Color(0xFFCE2B37) // https://radiomarconi1895.altervista.org/marconi/dpcm_14-06.html

internal fun thaiRed() = Color(0xFFA51931) // https://web.archive.org/web/20200527231905/https://www.nstda.or.th/th/nstda-knowledge/11557-thaiflag-color
internal fun thaiWhite() = Color(0xFFF4F5F8) // https://web.archive.org/web/20200527231905/https://www.nstda.or.th/th/nstda-knowledge/11557-thaiflag-color
internal fun thaiBlue() = Color(0xFF2D2A4A) // https://web.archive.org/web/20200527231905/https://www.nstda.or.th/th/nstda-knowledge/11557-thaiflag-color

internal fun ukBlue() = Color(0xFF00247D)
internal fun ukRed() = Color(0xFFCF142B)

internal fun usBlue() = Color(0xFF002147) // https://uk.usembassy.gov/u-s-flag-facts/
internal fun usRed() = Color(0xFFBB133E) // https://uk.usembassy.gov/u-s-flag-facts/
