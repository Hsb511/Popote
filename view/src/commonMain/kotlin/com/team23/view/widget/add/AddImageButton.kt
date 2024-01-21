package com.team23.view.widget.add

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.ds.icon.NeuracrIcon
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.extension.getImageMaxHeight

@Composable
fun AddImageButton(
    neuracrImageProperty: ImageProperty,
    onImageSelected: (ImageProperty) -> Unit,
    modifier: Modifier = Modifier,
) {
    /*
    val contentResolver = LocalContext.current.contentResolver
    val imageLauncher = rememberLauncherForActivityResult(PickVisualMedia()) { imageUri ->
        val imageProperty = getImagePropertyFromImageUri(imageUri, contentResolver)
        onImageSelected(imageProperty)
    } */

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
                // TODO
                // imageLauncher.launch(PickVisualMediaRequest(mediaType = ImageOnly))
            }
    ) {
        if (neuracrImageProperty is ImageProperty.None) {
            NeuracrIcon(
                iconProperty = IconProperty.Resource(
                    fileName = "drawable/ic_upload_image.xml",
                    tint = MaterialTheme.colorScheme.onSecondaryContainer,
                ),
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            NeuracrImage(
                neuracrImageProperty = neuracrImageProperty,
                maxImageHeight = getImageMaxHeight(),
            )
        }
    }
}

/*
private fun getImagePropertyFromImageUri(imageUri: Uri?, contentResolver: ContentResolver) = imageUri?.let {
	contentResolver.takePersistableUriPermission(imageUri, Intent.FLAG_GRANT_READ_URI_PERMISSION)
	contentResolver.query(imageUri, null, null, null, null)?.use { cursor ->
		val nameIndex = cursor.getColumnIndex(android.provider.MediaStore.Images.ImageColumns.DATA)
		cursor.moveToFirst()
		ImageProperty.UserPick(
			imageLocalUri = cursor.getString(nameIndex),
			contentDescription = null,
		)
	}
} ?: ImageProperty.None
*/
