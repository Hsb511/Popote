package com.team23.data.parsers

import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.BaseRecipeDataModel

internal class RecipeParser (
	private val instructionTitleParser: InstructionTitleParser,
) {
	fun toRecipeDataModel(recipeId: String, rawRecipe: Elements) = BaseRecipeDataModel(
		href = recipeId,
		imgSrc = rawRecipe.select("img").attr("src"),
		title = rawRecipe.select("h1").text(),
		subTitle = rawRecipe
			.select("p")
			.first()!!
			.text(),
		servingsAmount = rawRecipe
			.select("ingredient-list")
			.attr("servingnumber")
			.toInt(),
		instructionTitle =  instructionTitleParser.toCleanInstructionTitle(rawRecipe),
		lastTitle = rawRecipe
			.select("section.recipe__instructions")
			.html()
			.split("</div>")
			.last()
			.trim()
			.replace("<h2>", "")
			.replace("</h2>", ""),
		isSourceLocal = false,
		isTemporary = false,
	)
}
