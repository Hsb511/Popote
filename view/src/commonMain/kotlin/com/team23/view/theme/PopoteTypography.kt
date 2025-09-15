package com.team23.view.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
expect fun font(resId: String, weight: FontWeight): Font

@Composable
private fun neuracrFontFamily() = FontFamily.Default /* FontFamily(
	font("alice", titleFontWeight()),
	font("roboto_medium", boldFontWeight()),
	font("roboto_light", bodyFontWeight()),
)
*/

@Composable
internal fun neuracrTypography() = Typography(
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
