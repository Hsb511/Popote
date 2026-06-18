package com.team23.neuracrsrecipes.model.uimodel

import com.team23.neuracrsrecipes.model.property.FlagProperty

sealed interface TagUiModel {
	val label: String
	val isSelected: Boolean

	data class Label(
		override val label: String,
		override val isSelected: Boolean = false,
	): TagUiModel

	data class Flag(
		override val label: String,
		override val isSelected: Boolean = false,
		val flagProperty: FlagProperty,
	): TagUiModel
}
