package com.team23.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun PopoteTheme(content: @Composable () -> Unit) = MaterialTheme(
    colorScheme = neuracrColorScheme(),
    typography = neuracrTypography(),
    content = content,
)

@Composable
fun neuracrColorScheme(): ColorScheme =
    if (isSystemInDarkTheme()) {
        popoteDarkColorScheme
    } else {
        popoteLightColorScheme
    }
