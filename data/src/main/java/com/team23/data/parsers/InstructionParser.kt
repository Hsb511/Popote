package com.team23.data.parsers

import com.team23.data.models.InstructionDataModel
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
