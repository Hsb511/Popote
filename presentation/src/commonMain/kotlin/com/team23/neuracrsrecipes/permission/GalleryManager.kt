package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Composable
expect fun rememberGalleryManager(onResult: (ImageProperty.UserPick) -> Unit): GalleryManager


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class GalleryManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
