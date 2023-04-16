package com.example.design_system.theming

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp

val googleFontProvider = GoogleFont.Provider(
	providerAuthority = "com.google.android.gms.fonts",
	providerPackage = "com.google.android.gms",
	certificates = com.example.design_system.R.array.com_google_android_gms_fonts_certs
)

private fun neuracrFontFamily() = FontFamily(
	Font(GoogleFont("Alice", true), googleFontProvider, titleFontWeight()),
	Font(GoogleFont("Roboto", true), googleFontProvider, boldFontWeight()),
	Font(GoogleFont("Roboto", true), googleFontProvider, bodyFontWeight()),
)


internal val neuracrTypography = Typography(
	displaySmall = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = titleFontWeight(),
		fontSize = 32.sp,
	),
	headlineSmall = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = titleFontWeight(),
		fontSize = 26.sp,
	),
	titleLarge = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = titleFontWeight(),
		fontSize = 22.sp,
	),
	titleSmall = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = boldFontWeight(),
		fontSize = 18.sp,
	),
	bodyLarge = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = boldFontWeight(),
		fontSize = 16.sp,
	),
	bodyMedium = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = bodyFontWeight(),
		fontSize = 16.sp,
	),
	bodySmall = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = boldFontWeight(),
		fontSize = 14.sp,
	),
	labelLarge = TextStyle(
		fontFamily = neuracrFontFamily(),
		fontWeight = bodyFontWeight(),
		fontSize = 14.sp,
	),
)

private fun bodyFontWeight() = FontWeight.Light
private fun boldFontWeight() = FontWeight.Medium
private fun titleFontWeight() = FontWeight.Black
