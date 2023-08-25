package com.team23.presentation.recipe.mappers

import android.net.Uri
import com.team23.design_system.image.NeuracrImageProperty
import java.io.File
import javax.inject.Inject

class ImageMapper @Inject constructor() {
	fun toImageProperty(imageUri: String, contentDescription: String?): NeuracrImageProperty = when {
		imageUri.startsWith("http") -> NeuracrImageProperty.Remote(contentDescription, imageUri)
		else -> NeuracrImageProperty.UserPick(contentDescription, Uri.fromFile(File(imageUri)))
	}

	fun toImageUri(image: NeuracrImageProperty): String = when(image) {
		is NeuracrImageProperty.Remote -> image.url
		is NeuracrImageProperty.UserPick -> image.imageLocalUri.path.orEmpty()
		else -> ""
	}
}
