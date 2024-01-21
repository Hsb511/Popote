package com.team23.view.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getImageMaxHeight(): Dp = (getCurrentScreenWidth() - 64.dp) * 3 / 4

@Composable
expect fun getCurrentScreenWidth(): Dp
