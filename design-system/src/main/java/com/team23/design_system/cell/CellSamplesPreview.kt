package com.team23.design_system.cell

import com.team23.design_system.display.DisplayType
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty

fun getNeuracrCellPropertySample(displayType: DisplayType = DisplayType.BigCard) = NeuracrCellProperty(
	displayType = displayType,
	title = "bretzels",
	imageProperty = NeuracrImageProperty.Resource(
		contentDescription = null,
		imageRes = com.team23.design_system.R.drawable.bretzel
	),
	flagProperty = NeuracrFlagProperty.FRENCH,
	isFavorite = true,
	onFavoriteClick = {},
	isLocallySaved = true,
	onLocalPhoneClick = {},
)
