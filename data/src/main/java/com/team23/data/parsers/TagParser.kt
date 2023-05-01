package com.team23.data.parsers

import com.team23.data.models.TagDataModel
import org.jsoup.select.Elements
import javax.inject.Inject

class TagParser @Inject constructor() {
	fun toTagsDataModel(recipeId: String, rawRecipe: Elements): List<TagDataModel> = rawRecipe
		.select("li.tag-list__element")
		.toList()
		.map { it.text() }
		.map { tag ->
			TagDataModel(
				recipeId = recipeId,
				label = tag
			)
		}
}