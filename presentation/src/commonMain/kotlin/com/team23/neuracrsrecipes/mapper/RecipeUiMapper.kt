package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel.Source
import com.team23.neuracrsrecipes.extension.getLocalLanguage
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel

class RecipeUiMapper(
	private val dateUiMapper: DateUiMapper,
	private val ingredientUiMapper: IngredientUiMapper,
	private val instructionUiMapper: InstructionUiMapper,
	private val imageUiMapper: ImageUiMapper,
) {

	fun toRecipeUiModel(fullRecipe: RecipeDomainModel.Full) = with(fullRecipe) {
		RecipeUiModel(
			id = id,
			title = title,
			date = dateUiMapper.toSubtitleDate(date),
			author = author,
			tags = tags,
			image = imageUiMapper.toImageProperty(imageUrl, null),
			ingredients = ingredientUiMapper.toIngredientUiModels(ingredients),
			defaultServingsAmount = servingsNumber,
			instructions = instructionUiMapper.toInstructionUiModels(instructions),
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
			date = dateUiMapper.toLocalDate(recipeUiModel.date),
			author = author,
			tags = tags,
			imageUrl = imageUiMapper.toImageUri(image),
			ingredients = ingredientUiMapper.toIngredientDomainModels(ingredients),
			servingsNumber = defaultServingsAmount,
			instructions = instructionUiMapper.toInstructionDomainModels(instructions),
			startingText = description,
			endingText = conclusion,
			isFavorite = isFavorite,
			source = Source.Local.Temporary,
			language = when (getLocalLanguage()) {
				"fr" -> LanguageDomainModel.FRENCH
				else -> LanguageDomainModel.ENGLISH
			},
			sections = emptyList(),
		).also { println("HUGO - $recipeUiModel -> $it") }
	}
}
