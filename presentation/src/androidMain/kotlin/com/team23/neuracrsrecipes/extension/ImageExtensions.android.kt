package com.team23.neuracrsrecipes.extension

import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Images.Media
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.decodeBitmap

@Composable
actual fun getImageBitmapFromUri(uri: String): ImageBitmap {
    val contentResolver = LocalContext.current.contentResolver

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.createSource(contentResolver, Uri.parse(uri))
            .decodeBitmap { _, _ -> }
            .asImageBitmap()
    } else {
        Media
            .getBitmap(contentResolver, Uri.parse(uri))
            .asImageBitmap()
    }

}
