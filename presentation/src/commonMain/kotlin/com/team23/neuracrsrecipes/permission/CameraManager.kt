package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Composable
expect fun rememberCameraManager(onResult: (ImageProperty.UserPick) -> Unit): CameraManager


@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class CameraManager(
    onLaunch: () -> Unit
) {
    fun launch()
}
