package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Composable
expect fun rememberGalleryManager(onResult: (ImageProperty.UserPick) -> Unit): GalleryManager


expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
