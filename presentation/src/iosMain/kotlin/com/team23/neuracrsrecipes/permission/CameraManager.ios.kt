package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.ImageProperty


@Composable
actual fun rememberCameraManager(onResult: (ImageProperty.UserPick) -> Unit): CameraManager {
    TODO("Not yet implemented")
}

actual class CameraManager actual constructor(onLaunch: () -> Unit) {
    actual fun launch() {
    }
}
