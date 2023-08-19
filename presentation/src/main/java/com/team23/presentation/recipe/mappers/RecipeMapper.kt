package com.team23.presentation.recipe.mappers

import com.team23.design_system.image.NeuracrImageProperty
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.models.RecipeDomainModel.Source
import com.team23.presentation.recipe.models.RecipeUiModel
import javax.inject.Inject

class RecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val ingredientMapper: IngredientMapper,
	private val instructionMapper: InstructionMapper,
) {
	fun toRecipeUiModel(fullRecipe: RecipeDomainModel.Full) = with(fullRecipe) {
		RecipeUiModel(
			id = id,
			title = title,
			date = dateMapper.toSubtitleDate(date, language),
			author = author,
			tags = tags,
			image = NeuracrImageProperty.Remote(null, imageUrl),
			ingredients = ingredientMapper.toIngredientUiModels(ingredients),
			defaultServingsAmount = servingsNumber,
			instructions = instructionMapper.toInstructionUiModels(instructions),
			description = startingText,
			conclusion = endingText,
			isFavorite = isFavorite,
			isLocallySaved = source is Source.Local.Saved,
		)
	}
}
