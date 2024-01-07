package com.team23.neuracrsrecipes.mapper

import com.team23.neuracrsrecipes.model.property.ImageProperty

class ImageMapper {

	fun toImageProperty(imageUri: String, contentDescription: String?): ImageProperty = when {
		// TODO CONFIGURE KAMEL WITH HTTP CLIENT
		true -> ImageProperty.Resource(contentDescription, "drawable/bretzel.jpg")
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
