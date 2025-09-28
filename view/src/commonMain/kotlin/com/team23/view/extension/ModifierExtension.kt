package com.team23.view.extension

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getTopScreenHeight(additionalHeight: Dp = 0.dp): Dp {
    return 32.dp + additionalHeight + WindowInsets.statusBars.asPaddingValues().calculateTopPadding()
}

@Composable
fun Modifier.topScreenHeight(additionalHeight: Dp = 0.dp): Modifier {
    return this.height(getTopScreenHeight(additionalHeight))
}
