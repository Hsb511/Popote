package com.example.data.mappers

import com.example.data.datasources.NeuracrWebsiteDataSource
import com.example.data.models.FullRecipeDataModel
import com.example.domain.models.RecipeDomainModel
import javax.inject.Inject

class FullRecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val languageMapper: LanguageMapper,
	private val ingredientMapper: IngredientMapper,
) {
	fun toFullRecipeDomainModel(fullRecipeDataModel: FullRecipeDataModel) = RecipeDomainModel.Full(
		id = fullRecipeDataModel.recipe.href,
		title = fullRecipeDataModel.recipe.title,
		imageUrl = "${NeuracrWebsiteDataSource.NEURACR_WEBSITE_HOME_URL}${fullRecipeDataModel.recipe.imgSrc}",
		date = dateMapper.toLocalDate(dateStr = fullRecipeDataModel.recipe.href
			.split("/recipes/")[1]
			.split("/")
			.let { splitData -> "${splitData[0]}/${splitData[1]}/${splitData[2]}" }
		),
		language = languageMapper.toLanguageDomainModel(fullRecipeDataModel.recipe.href),
		author = fullRecipeDataModel.recipe.subTitle.split(" - Written by ").last(),
		tags = fullRecipeDataModel.tags.map { it.label },
		servingsNumber = 0,
		ingredients = ingredientMapper.toIngredientListDomainModel(fullRecipeDataModel.ingredients),
		startingText = fullRecipeDataModel.recipe.instructionTitle,
		instructions = listOf(),
		endingText = fullRecipeDataModel.recipe.lastTitle,
		sections = listOf()
	)
}
