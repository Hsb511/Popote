package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel.Source
import com.team23.domain.recipe.model.TagDomainModel
import com.team23.neuracrsrecipes.extension.getLocalLanguage
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

class RecipeUiMapper(
	private val dateUiMapper: DateUiMapper,
	private val ingredientUiMapper: IngredientUiMapper,
	private val instructionUiMapper: InstructionUiMapper,
	private val imageUiMapper: ImageUiMapper,
	private val tagUiMapper: TagUiMapper,
) {

	fun toRecipeUiModel(fullRecipe: RecipeDomainModel.Full) = with(fullRecipe) {
		RecipeUiModel(
			id = id,
			title = title,
			date = dateUiMapper.toSubtitleDate(date),
			author = author,
			tags = tagUiMapper.toTagUiModels(tags),
			image = imageUiMapper.toImageProperty(imageUrl, null),
			ingredients = ingredientUiMapper.toIngredientUiModels(ingredients),
			defaultServingsAmount = servingsNumber,
			instructions = instructionUiMapper.toInstructionUiModels(instructions),
			description = startingText,
			conclusion = endingText,
			isFavorite = isFavorite,
			isLocallySaved = source is Source.Local.Saved,
			isInGroceryList = isInGroceryList,
		)
	}

	fun toSummarizedRecipeUiModel(fullRecipe: RecipeDomainModel.Full): SummarizedRecipeUiModel =  with(fullRecipe) {
		SummarizedRecipeUiModel(
			id = id,
			title = title,
			imageProperty = imageUiMapper.toImageProperty(imageUrl, title),
			cuisineFlag = null,
			languageFlag = when (language) {
				LanguageDomainModel.ENGLISH -> FlagProperty.UK_US
				LanguageDomainModel.FRENCH -> FlagProperty.FRENCH
			},
			isFavorite = isFavorite,
			isLocallySaved = source is Source.Local.Saved,
			isInGroceryList = isInGroceryList,
		)
	}

	fun toRecipeDomainModel(recipeUiModel: RecipeUiModel) = with(recipeUiModel) {
		RecipeDomainModel.Full(
			id = id,
			title = title,
			date = dateUiMapper.toLocalDate(recipeUiModel.date),
			author = author,
			tags = tags.map { TagDomainModel.Normal(it.label) },
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
			isInGroceryList = isInGroceryList,
		)
	}
}
