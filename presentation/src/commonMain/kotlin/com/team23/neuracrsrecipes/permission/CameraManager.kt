package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Composable
expect fun rememberCameraManager(onResult: (ImageProperty.UserPick) -> Unit): CameraManager


expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
