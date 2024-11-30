package com.team23.view.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getImageMaxHeight(): Dp = (getCurrentScreenWidth() - horizontalGutterPadding * 2) * 3 / 4

@Composable
expect fun getCurrentScreenWidth(): Dp

internal val horizontalGutterPadding = 32.dp

@Composable
expect fun BackHandler(enabled: Boolean, onBackPressed: () -> Unit)