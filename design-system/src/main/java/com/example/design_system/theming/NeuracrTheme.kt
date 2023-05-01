package com.example.design_system.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
            setStatusBarColor(color = black26())
            setNavigationBarColor(color = black35())
        }
        neuracrDarkColorScheme
    } else {
        systemUiController.apply {
            setStatusBarColor(color = umber())
            setNavigationBarColor(color = black90())
        }
        neuracrLightColorScheme
    }
}