package com.example.data.parsers

import com.example.data.models.BaseRecipeDataModel
import org.jsoup.select.Elements
import javax.inject.Inject

class RecipeParser @Inject constructor() {
	fun toRecipeDataModel(recipeId: String, rawRecipe: Elements) = BaseRecipeDataModel(
		href = recipeId,
		imgSrc = rawRecipe.select("img").attr("src"),
		title = rawRecipe.select("h1").text(),
		subTitle = rawRecipe.select("p").first()!!.text(),
		instructionTitle = rawRecipe
			.select("section.recipe__instructions")
			.html()
			.split("<h2>")
			.first()
			.split("<br>")
			.first(),
		lastTitle = rawRecipe
			.select("section.recipe__instructions")
			.html()
			.split("</div>")
			.last(),
	)
}
