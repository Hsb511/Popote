package com.team23.presentation.search.models

import com.team23.presentation.home.models.SummarizedRecipeUiModel

data class SearchUiModel(
	val textField: TextFieldUiModel,
	val tagsRow: TagsRowUiModel,
	val recipes: List<SummarizedRecipeUiModel>,
	val onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	val onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
)

data class TextFieldUiModel(
	val searchValue: String,
	val onValueChange: (String) -> Unit,
)

data class TagsRowUiModel(
	val tags: List<TagUiModel>,
	val onTagSelected: (TagUiModel) -> Unit,
)