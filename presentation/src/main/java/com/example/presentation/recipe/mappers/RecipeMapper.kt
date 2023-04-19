package com.example.presentation.recipe.mappers

import com.example.design_system.image.NeuracrImageProperty
import com.example.domain.models.RecipeDomainModel
import com.example.presentation.recipe.models.RecipeUiModel
import javax.inject.Inject

class RecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val ingredientMapper: IngredientMapper,
) {
	fun toRecipeUiModel(fullRecipe: RecipeDomainModel.Full) = RecipeUiModel(
		title = fullRecipe.title,
		date = dateMapper.toSubtitleDate(fullRecipe.date, fullRecipe.language),
		author = fullRecipe.author,
		tags = fullRecipe.tags,
		image = NeuracrImageProperty.Remote(null, fullRecipe.imageUrl),
		ingredients = ingredientMapper.toIngredientUiModel(fullRecipe.ingredients),
		defaultServingsAmount = fullRecipe.servingsNumber,
		description = fullRecipe.startingText,
		conclusion = fullRecipe.endingText,
	)
}