package com.team23.view.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
actual fun getCurrentScreenWidth(): Dp =
    LocalConfiguration.current.screenWidthDp.dp
