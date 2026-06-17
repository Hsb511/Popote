package com.team23.neuracrsrecipes.model.uimodel

import androidx.compose.runtime.Immutable
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.IconProperty

@Immutable
data class SearchUiModel(
	val textField: TextFieldUiModel,
	val tagsRow: TagsRowUiModel,
	val items: List<Item>,
) {
	data class Item(
		val id: String,
		val cellProperty: CellProperty,
	)
}

@Immutable
data class TextFieldUiModel(
	val searchValue: String,
	val label: String,
	val placeholder: String,
	val leadingIcon: IconProperty,
)

@Immutable
data class TagsRowUiModel(
	val tags: List<TagUiModel>,
)