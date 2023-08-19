package com.team23.design_system.cell

import com.team23.design_system.display.DisplayType
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty

data class NeuracrCellProperty(
	val displayType: DisplayType,
	val imageProperty: NeuracrImageProperty,
	val title: String,
	val flagProperty: NeuracrFlagProperty,
	val isFavorite: Boolean,
	val isLocallySaved: Boolean,
	val onFavoriteClick: () -> Unit,
)
