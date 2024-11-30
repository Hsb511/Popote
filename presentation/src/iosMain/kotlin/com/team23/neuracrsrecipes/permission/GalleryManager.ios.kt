package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Composable
actual fun rememberGalleryManager(onResult: (ImageProperty.UserPick) -> Unit): GalleryManager {
    TODO("Not yet implemented")
}

actual class GalleryManager actual constructor(onLaunch: () -> Unit) {
    actual fun launch() {
    }
}

