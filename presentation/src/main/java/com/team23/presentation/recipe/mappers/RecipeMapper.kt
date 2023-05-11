package com.team23.presentation.recipe.mappers

import com.team23.design_system.image.NeuracrImageProperty
import com.team23.domain.models.RecipeDomainModel
import com.team23.presentation.recipe.models.RecipeUiModel
import javax.inject.Inject

class RecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val ingredientMapper: IngredientMapper,
	private val instructionMapper: InstructionMapper,
) {
	fun toRecipeUiModel(fullRecipe: RecipeDomainModel.Full) = RecipeUiModel(
		title = fullRecipe.title,
		date = dateMapper.toSubtitleDate(fullRecipe.date, fullRecipe.language),
		author = fullRecipe.author,
		tags = fullRecipe.tags,
		image = NeuracrImageProperty.Remote(null, fullRecipe.imageUrl),
		ingredients = ingredientMapper.toIngredientUiModels(fullRecipe.ingredients),
		defaultServingsAmount = fullRecipe.servingsNumber,
		instructions = instructionMapper.toInstructionUiModels(fullRecipe.instructions),
		description = fullRecipe.startingText,
		conclusion = fullRecipe.endingText,
		isFavorite = fullRecipe.isFavorite,
	)
}
