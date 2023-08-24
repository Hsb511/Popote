package com.team23.data.mappers

import com.team23.data.datasources.NeuracrWebsiteDataSource
import com.team23.data.models.BaseRecipeDataModel
import com.team23.data.models.FullRecipeDataModel
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.models.RecipeDomainModel.Source
import javax.inject.Inject

class FullRecipeMapper @Inject constructor(
	private val dateMapper: DateMapper,
	private val languageMapper: LanguageMapper,
	private val ingredientMapper: IngredientMapper,
	private val instructionMapper: InstructionMapper,
	private val tagMapper: TagMapper,
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
		author = fullRecipeDataModel.recipe.subTitle.split(SUBTITLE_DELIMITER).last(),
		tags = tagMapper.toTagDomainModel(fullRecipeDataModel.tags),
		servingsNumber = fullRecipeDataModel.recipe.servingsAmount,
		ingredients = ingredientMapper.toIngredientListDomainModel(fullRecipeDataModel.ingredients),
		startingText = fullRecipeDataModel.recipe.instructionTitle,
		instructions = instructionMapper.toInstructionListDomainModel(fullRecipeDataModel.instructions),
		endingText = fullRecipeDataModel.recipe.lastTitle,
		sections = listOf(),
		isFavorite = false,
		source = Source.Remote,
	)

	fun toFullRecipeDataModel(fullRecipeDomainModel: RecipeDomainModel.Full) = with(fullRecipeDomainModel) {
		FullRecipeDataModel(
			recipe = BaseRecipeDataModel(
				href = id,
				imgSrc = imageUrl,
				title = title,
				subTitle = dateMapper.toDateString(fullRecipeDomainModel.date) + SUBTITLE_DELIMITER + fullRecipeDomainModel.author,
				servingsAmount = servingsNumber,
				instructionTitle = startingText,
				lastTitle = endingText,
				isSourceLocal = source is Source.Local,
				isTemporary = source is Source.Local.Temporary,
			),
			tags = tagMapper.toTagDataModel(id, tags),
			ingredients = ingredientMapper.toIngredientListDataModel(id, ingredients),
			instructions = instructionMapper.toInstructionListDataModel(id, instructions),
		)
	}
}

private const val SUBTITLE_DELIMITER = " - Written by "
