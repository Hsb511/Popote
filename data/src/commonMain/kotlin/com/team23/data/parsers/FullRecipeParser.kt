package com.team23.data.parsers

import com.fleeksoft.ksoup.select.Elements
import com.team23.data.models.FullRecipeDataModel

internal class FullRecipeParser(
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
