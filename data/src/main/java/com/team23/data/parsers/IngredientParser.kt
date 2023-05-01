package com.team23.data.parsers

import com.team23.data.models.IngredientDataModel
import org.jsoup.select.Elements
import javax.inject.Inject

class IngredientParser @Inject constructor() {
	fun toIngredientsDataModel(
		recipeId: String,
		rawRecipe: Elements
	): List<IngredientDataModel> = rawRecipe
		.select("ingredient-item")
		.map { element ->
			IngredientDataModel(
				recipeId = recipeId,
				label = element.attr("name"),
				quantity = element.attr("initialquantity"),
				unit = element.attr("unit"),
			)
		}
}
