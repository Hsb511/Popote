package com.team23.neuracrsrecipes.model.property

sealed class ImageProperty(open val contentDescription: String?) {
	data object None : ImageProperty(null)

	data class Remote(
		override val contentDescription: String?,
		val url: String,
	) : ImageProperty(contentDescription)

	data class Resource(
		override val contentDescription: String?,
		val imageRes: String,
	) : ImageProperty(contentDescription)

	data class UserPick(
		override val contentDescription: String?,
		val imageLocalUri: String,
	) : ImageProperty(contentDescription)
}
