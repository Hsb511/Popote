package com.team23.neuracrsrecipes.extension

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap

@Composable
expect fun getImageBitmapFromUri(uri: String): ImageBitmap
