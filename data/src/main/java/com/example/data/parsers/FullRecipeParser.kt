package com.example.data.parsers

import com.example.data.models.FullRecipeDataModel
import org.jsoup.select.Elements
import javax.inject.Inject

class FullRecipeParser @Inject constructor(
	private val recipeParser: RecipeParser,
	private val tagParser: TagParser,
	private val ingredientParser: IngredientParser,
) {
	fun toFullRecipeDataModel(recipeId: String, rawRecipe: Elements) = FullRecipeDataModel(
		recipe = recipeParser.toRecipeDataModel(recipeId, rawRecipe),
		tags = tagParser.toTagsDataModel(recipeId, rawRecipe),
		ingredients = ingredientParser.toIngredientsDataModel(recipeId, rawRecipe),
		instructions = listOf(),
	)
}
