package com.team23.view.sample.property

import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.FlagProperty

fun getCellPropertySample(displayType: DisplayType = DisplayType.BigCard) = CellProperty(
	id = "",
	displayType = displayType,
	title = "bretzels",
	imageProperty = resourceImagePreviewSample,
	languageFlag = FlagProperty.FRENCH,
	isLocallySaved = true,
	localPhone = CellProperty.LocalPhone(localPhoneButtonPreviewSample),
	favorite = CellProperty.Favorite(favoriteButtonPreviewSample),
)
