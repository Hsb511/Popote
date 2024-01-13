package com.team23.view.navigation

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import com.team23.view.navigation.screen.HomeScreen

@Composable
fun PopoteNavigator(content: @Composable (Navigator) -> Unit) {
    Navigator(screen = HomeScreen) { navigator ->
        content(navigator)
    }
}
