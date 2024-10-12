package com.team23.neuracrsrecipes.permission

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Composable
actual fun rememberGalleryManager(onResult: (ImageProperty.UserPick) -> Unit): GalleryManager {
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            uri?.let {
                onResult.invoke(
                    ImageProperty.UserPick(
                        contentDescription = null,
                        uri = uri.toString(),
                    )
                )
            }
        }
    return remember {
        GalleryManager(onLaunch = {
            galleryLauncher.launch(
                PickVisualMediaRequest(
                    mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly
                )
            )
        })
    }
}

actual class GalleryManager actual constructor(private val onLaunch: () -> Unit) {
    actual fun launch() {
        onLaunch()
    }
}
