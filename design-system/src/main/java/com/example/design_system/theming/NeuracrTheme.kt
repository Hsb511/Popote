package com.example.design_system.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun NeuracrTheme(content: @Composable () -> Unit) = MaterialTheme(
    colorScheme = neuracrColorScheme(),
    typography = neuracrTypography,
    content = content,
)

@Composable
fun neuracrColorScheme(): ColorScheme {
    val systemUiController = rememberSystemUiController()
    return if (isSystemInDarkTheme()) {
        systemUiController.apply {
            setSystemBarsColor(color = black26())
        }
        neuracrDarkColorScheme
    } else {
        systemUiController.apply {
            setSystemBarsColor(color = umber())
        }
        neuracrLightColorScheme
    }
}