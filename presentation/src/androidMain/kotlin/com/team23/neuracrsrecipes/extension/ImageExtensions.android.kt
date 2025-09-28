package com.team23.neuracrsrecipes.extension

import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore.Images.Media
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.decodeBitmap
import androidx.core.net.toUri

@Composable
actual fun getImageBitmapFromUri(uri: String): ImageBitmap? {
    val contentResolver = LocalContext.current.contentResolver

    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        runCatching {
            ImageDecoder.createSource(contentResolver, uri.toUri())
                .decodeBitmap { _, _ -> }
                .asImageBitmap()
        }.getOrNull()
    } else {
        Media
            .getBitmap(contentResolver, uri.toUri())
            .asImageBitmap()
    }

}
