package com.team23.neuracrsrecipes.model.uimodel

import com.team23.neuracrsrecipes.model.property.IconProperty

data class SearchUiModel(
	val textField: TextFieldUiModel,
	val tagsRow: TagsRowUiModel,
	val recipes: List<SummarizedRecipeUiModel>,
	val onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	val onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	val onLocalPhoneClick: () -> Unit,
)

data class TextFieldUiModel(
	val searchValue: String,
	val onValueChange: (String) -> Unit,
	val label: String,
	val placeholder: String,
	val leadingIcon: IconProperty,
)

data class TagsRowUiModel(
	val tags: List<TagUiModel>,
	val onTagSelected: (TagUiModel) -> Unit,
)