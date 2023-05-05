package com.team23.presentation.search.mappers

import com.team23.presentation.search.models.TagUiModel
import javax.inject.Inject

class TagMapper @Inject constructor() {
	fun toTagUiModels(tags: List<String>): List<TagUiModel> = tags.map { tagString ->
		TagUiModel(
			label = tagString,
			isSelected = false,
		)
	}
}
