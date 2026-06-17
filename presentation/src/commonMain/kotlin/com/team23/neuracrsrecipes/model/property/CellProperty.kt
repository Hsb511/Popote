package com.team23.neuracrsrecipes.model.property

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import com.team23.neuracrsrecipes.model.action.CellAction

@Immutable
data class CellProperty(
	val id: String,
	val displayType: DisplayType,
	val imageProperty: ImageProperty,
	val title: String,
	val flagProperty: FlagProperty,
	val isLocallySaved: Boolean,
	val localPhone: LocalPhone,
	val favorite: Favorite,
	val maxHeight: Dp? = null,
) {

	@Immutable
	data class LocalPhone(
		val iconProperty: IconProperty,
		val action: CellAction = CellAction.LocalPhoneClick,
	)

	@Immutable
	data class Favorite(
		val iconProperty: IconProperty.Vector,
		val action: CellAction = CellAction.FavoriteClick,
	)
}
