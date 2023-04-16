package com.example.data.parsers

import com.example.data.models.TagDataModel
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