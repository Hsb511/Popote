package com.team23.presentation.recipe.mappers

import com.team23.domain.models.LanguageDomainModel
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.models.RecipeDomainModel.Source
import com.team23.presentation.recipe.models.RecipeUiModel
import java.util.Locale
import javax.inject.Inject

class RecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val ingredientMapper: IngredientMapper,
	private val instructionMapper: InstructionMapper,
	private val imageMapper: ImageMapper,
) {
	fun toRecipeUiModel(fullRecipe: RecipeDomainModel.Full) = with(fullRecipe) {
		RecipeUiModel(
			id = id,
			title = title,
			date = dateMapper.toSubtitleDate(date, language),
			author = author,
			tags = tags,
			image = imageMapper.toImageProperty(imageUrl, null),
			ingredients = ingredientMapper.toIngredientUiModels(ingredients),
			defaultServingsAmount = servingsNumber,
			instructions = instructionMapper.toInstructionUiModels(instructions),
			description = startingText,
			conclusion = endingText,
			isFavorite = isFavorite,
			isLocallySaved = source is Source.Local.Saved,
		)
	}

	fun toRecipeDomainModel(recipeUiModel: RecipeUiModel) = with(recipeUiModel) {
		RecipeDomainModel.Full(
			id = id,
			title = title,
			date = dateMapper.toLocalDate(recipeUiModel.date),
			author = author,
			tags = tags,
			imageUrl = imageMapper.toImageUri(image),
			ingredients = ingredientMapper.toIngredientDomainModels(ingredients),
			servingsNumber = defaultServingsAmount,
			instructions = instructionMapper.toInstructionDomainModels(instructions),
			startingText = description,
			endingText = conclusion,
			isFavorite = isFavorite,
			source = Source.Local.Temporary,
			language = when (Locale.getDefault().language) {
				"fr" -> LanguageDomainModel.FRENCH
				else -> LanguageDomainModel.ENGLISH
			},
			sections = emptyList(),
		)
	}
}
