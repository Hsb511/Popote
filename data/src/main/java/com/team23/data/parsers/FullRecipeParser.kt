package com.team23.data.parsers

import com.team23.data.models.FullRecipeDataModel
import org.jsoup.select.Elements
import javax.inject.Inject

class FullRecipeParser @Inject constructor(
	private val recipeParser: RecipeParser,
	private val tagParser: TagParser,
	private val ingredientParser: IngredientParser,
	private val instructionParser: InstructionParser,
) {
	fun toFullRecipeDataModel(recipeId: String, rawRecipe: Elements) = FullRecipeDataModel(
		recipe = recipeParser.toRecipeDataModel(recipeId, rawRecipe),
		tags = tagParser.toTagsDataModel(recipeId, rawRecipe),
		ingredients = ingredientParser.toIngredientsDataModel(recipeId, rawRecipe),
		instructions = instructionParser.toInstructionsDataModel(recipeId, rawRecipe),
	)
}
