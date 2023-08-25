package com.team23.presentation.add.views

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia.ImageOnly
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R

@Composable
fun AddImageButton(
	onImageSelected: (NeuracrImageProperty) -> Unit,
	modifier: Modifier = Modifier,
) {
	val result = remember { mutableStateOf<NeuracrImageProperty?>(null) }
	val neuracrImageProperty = result.value
	val imageLauncher = rememberLauncherForActivityResult(PickVisualMedia()) { imageUri ->
		val imageProperty = imageUri?.let {
			NeuracrImageProperty.UserPick(
				imageLocalUri = imageUri,
				contentDescription = null,
			)
		} ?: NeuracrImageProperty.None
		result.value = imageProperty
		onImageSelected(imageProperty)
	}

	Box(
		modifier = modifier
			.aspectRatio(ratio = 4 / 3f, matchHeightConstraintsFirst = true)
			.border(width = 2.dp, color = MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium)
			.clip(shape = MaterialTheme.shapes.medium)
			.background(color = MaterialTheme.colorScheme.surface)
			.clickable { imageLauncher.launch(PickVisualMediaRequest(mediaType = ImageOnly)) }
	) {
		if (neuracrImageProperty == null || neuracrImageProperty is NeuracrImageProperty.None) {
			Icon(
				painter = painterResource(id = R.drawable.ic_upload_image),
				contentDescription = null,
				tint = MaterialTheme.colorScheme.tertiary,
				modifier = Modifier.align(Alignment.Center)
			)
		} else {
			NeuracrImage(
				neuracrImageProperty = neuracrImageProperty,
				maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
fun AddImageButtonPreview() {
	NeuracrTheme {
		AddImageButton({})
	}
}
