package com.team23.data.mappers

import com.team23.data.models.TagDataModel
import javax.inject.Inject

class TagMapper @Inject constructor() {
	fun toTagDomainModel(tags: List<TagDataModel>): List<String> =
		tags.map { it.label }
}