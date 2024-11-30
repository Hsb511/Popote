package com.team23.view.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.navigator.Navigator
import com.team23.view.navigation.screen.HomeScreen

@Composable
fun PopoteNavigator(
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    title: MutableState<String?>,
    content: @Composable (Navigator) -> Unit,
) {
    Navigator(screen = HomeScreen) { navigator ->
        content(navigator)
    }
}
