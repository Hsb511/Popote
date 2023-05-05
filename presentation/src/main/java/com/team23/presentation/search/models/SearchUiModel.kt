package com.team23.presentation.search.models

import com.team23.presentation.home.models.SummarizedRecipeUiModel

data class SearchUiModel(
	val searchValue: String,
	val onValueChange: (String) -> Unit,
	val tags: List<TagUiModel>,
	val onTagSelected: (TagUiModel) -> Unit,
	val recipes: List<SummarizedRecipeUiModel>,
)
