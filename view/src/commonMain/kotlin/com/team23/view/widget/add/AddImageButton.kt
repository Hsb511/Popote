package com.team23.view.widget.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.permission.PermissionCallback
import com.team23.neuracrsrecipes.permission.PermissionStatus
import com.team23.neuracrsrecipes.permission.PermissionType
import com.team23.neuracrsrecipes.permission.createPermissionsManager
import com.team23.neuracrsrecipes.permission.rememberCameraManager
import com.team23.neuracrsrecipes.permission.rememberGalleryManager
import com.team23.view.Res
import com.team23.view.ds.icon.PopoteIcon
import com.team23.view.ds.image.PopoteImage
import com.team23.view.extension.getImageMaxHeight
import com.team23.view.ic_upload_image
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun AddImageButton(
    neuracrImageProperty: ImageProperty,
    modifier: Modifier = Modifier,
    launchSettings: () -> Unit = {},
    onImageSelected: (ImageProperty) -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    var imageBitmap by remember { mutableStateOf<ImageProperty.UserPick?>(null) }
    var imageSourceOptionDialog by remember { mutableStateOf(value = false) }
    var launchCamera by remember { mutableStateOf(value = false) }
    var launchGallery by remember { mutableStateOf(value = false) }
    var launchSetting by remember { mutableStateOf(value = false) }
    var permissionRationalDialog by remember { mutableStateOf(value = false) }
    val permissionsManager = createPermissionsManager(object : PermissionCallback {
        override fun onPermissionStatus(
            permissionType: PermissionType,
            status: PermissionStatus
        ) {
            when (status) {
                PermissionStatus.GRANTED -> {
                    when (permissionType) {
                        PermissionType.CAMERA -> launchCamera = true
                        PermissionType.GALLERY -> launchGallery = true
                    }
                }

                else -> {
                    permissionRationalDialog = true
                }
            }
        }
    })
    val cameraManager = rememberCameraManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) { it }
            imageBitmap = bitmap
            println("HUGO - $bitmap")
            onImageSelected(bitmap)
        }
    }

    val galleryManager = rememberGalleryManager {
        coroutineScope.launch {
            val bitmap = withContext(Dispatchers.Default) { it }
            imageBitmap = bitmap
            println("HUGO - $bitmap")
            onImageSelected(bitmap)
        }
    }
    if (imageSourceOptionDialog) {
        ImageSourceOptionDialog(
            onDismissRequest = { imageSourceOptionDialog = false },
            onGalleryRequest = {
                imageSourceOptionDialog = false
                launchGallery = true
            },
            onCameraRequest = {
                imageSourceOptionDialog = false
                launchCamera = true
            }
        )
    }
    if (launchGallery) {
        if (permissionsManager.isPermissionGranted(PermissionType.GALLERY)) {
            galleryManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.GALLERY)
        }
        launchGallery = false
    }
    if (launchCamera) {
        if (permissionsManager.isPermissionGranted(PermissionType.CAMERA)) {
            cameraManager.launch()
        } else {
            permissionsManager.askPermission(PermissionType.CAMERA)
        }
        launchCamera = false
    }
    if (launchSetting) {
        launchSettings()
        launchSetting = false
    }

    Box(
        modifier = modifier
            .aspectRatio(ratio = 4 / 3f, matchHeightConstraintsFirst = true)
            .border(
                width = 2.dp,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clip(shape = MaterialTheme.shapes.medium)
            .background(color = MaterialTheme.colorScheme.surface)
            .clickable {
                imageSourceOptionDialog = true
            }
    ) {
        if (neuracrImageProperty is ImageProperty.None) {
            PopoteIcon(
                iconProperty = IconProperty.Resource(
                    drawableResource = Res.drawable.ic_upload_image,
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            PopoteImage(
                neuracrImageProperty = neuracrImageProperty,
                maxImageHeight = getImageMaxHeight(),
            )
        }
    }
}
