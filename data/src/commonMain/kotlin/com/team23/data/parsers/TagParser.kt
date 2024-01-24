package com.team23.data.parsers

import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.TagDataModel

internal class TagParser {
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
