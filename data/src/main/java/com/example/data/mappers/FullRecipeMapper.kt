package com.example.data.mappers

import com.example.data.datasources.NeuracrWebsiteDataSource
import com.example.data.models.FullRecipeDataModel
import com.example.domain.models.RecipeDomainModel
import javax.inject.Inject

class FullRecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val languageMapper: LanguageMapper,
) {
	fun toFullRecipeDomainModel(fullRecipeDataModel: FullRecipeDataModel) = RecipeDomainModel.Full(
		id = fullRecipeDataModel.href,
		title = fullRecipeDataModel.title,
		imageUrl = "${NeuracrWebsiteDataSource.NEURACR_WEBSITE_HOME_URL}${fullRecipeDataModel.imgSrc}",
		date = dateMapper.toLocalDate(dateStr = fullRecipeDataModel.href
			.split("/recipes/")[1]
			.split("/")
			.let { splitData -> "${splitData[0]}/${splitData[1]}/${splitData[2]}" }
		),
		language = languageMapper.toLanguageDomainModel(fullRecipeDataModel.href),
		author = fullRecipeDataModel.subTitle.split(" - Written by ").last(), // TODO
		tags = listOf(),
		servingsNumber = 0,
		ingredients = listOf(),
		startingText = fullRecipeDataModel.instructionTitle,
		instructions = listOf(),
		endingText = fullRecipeDataModel.lastTitle,
		sections = listOf()
	)
}
