package com.example.data.parsers

import com.example.data.models.InstructionDataModel
import org.jsoup.select.Elements
import javax.inject.Inject

class InstructionParser @Inject constructor() {
	fun toInstructionsDataModel(
		recipeId: String,
		rawRecipe: Elements
	): List<InstructionDataModel> = rawRecipe
		.select("ol")
		.select("li")
		.mapIndexed { index, element ->
			InstructionDataModel(
				recipeId = recipeId,
				label = element.text(),
				order = index,
			)
		}
}
