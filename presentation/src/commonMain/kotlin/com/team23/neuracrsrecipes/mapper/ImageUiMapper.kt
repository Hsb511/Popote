package com.team23.neuracrsrecipes.mapper

import com.team23.neuracrsrecipes.model.property.ImageProperty

class ImageUiMapper {

	fun toImageProperty(imageUri: String, contentDescription: String?): ImageProperty = when {
		imageUri.isEmpty() -> ImageProperty.None
		imageUri.startsWith("http") -> ImageProperty.Remote(contentDescription, imageUri)
		else -> ImageProperty.UserPick(contentDescription, imageUri)
	}

	fun toImageUri(image: ImageProperty): String = when(image) {
		is ImageProperty.Remote -> image.url
		is ImageProperty.UserPick -> image.imageLocalUri
		else -> ""
	}
}
