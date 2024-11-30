package com.team23.neuracrsrecipes.model.property

data class CellProperty(
	val displayType: DisplayType,
	val imageProperty: ImageProperty,
	val title: String,
	val flagProperty: FlagProperty,
	val isLocallySaved: Boolean,
	val localPhone: LocalPhone,
	val favorite: Favorite,
) {

	data class LocalPhone(
		val iconProperty: IconProperty,
		val onLocalPhoneClick: () -> Unit,
	)

	data class Favorite(
		val iconProperty: IconProperty.Vector,
		val onFavoriteClick: () -> Unit,
	)
}
