package com.team23.neuracrsrecipes.extension

import android.net.Uri
import android.provider.MediaStore.Images.Media
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getImageBitmapFromUri(uri: String): ImageBitmap {
    val contentResolver = LocalContext.current.contentResolver
    return Media.getBitmap(contentResolver, Uri.parse(uri)).asImageBitmap()
}
