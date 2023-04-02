package com.example.design_system.theming

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NeuracrTheme(content: @Composable () -> Unit) = MaterialTheme(
    colorScheme = if (isSystemInDarkTheme()) neuracrDarkColorScheme else neuracrLightColorScheme,
    typography = neuracrTypography,
    content = content,
)
