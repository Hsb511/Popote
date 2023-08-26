package com.team23.data.mappers

import com.team23.data.models.BaseRecipeDataModel
import com.team23.data.models.FullRecipeDataModel
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.models.RecipeDomainModel.Source
import javax.inject.Inject

class FullRecipeMapper @Inject constructor(
	private val imageMapper: ImageMapper,
	private val dateMapper: DateMapper,
	private val languageMapper: LanguageMapper,
	private val ingredientMapper: IngredientMapper,
	private val instructionMapper: InstructionMapper,
	private val sourceMapper: SourceMapper,
	private val tagMapper: TagMapper,
) {
	fun toFullRecipeDomainModel(fullRecipeDataModel: FullRecipeDataModel) = RecipeDomainModel.Full(
		id = fullRecipeDataModel.recipe.href,
		title = fullRecipeDataModel.recipe.title,
		imageUrl = imageMapper.toImageUrl(fullRecipeDataModel.recipe.imgSrc),
		date = dateMapper.toLocalDateFromSubtitleDate(fullRecipeDataModel.recipe.subTitle.split(" - ")[0]),
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
		source = sourceMapper.toDomainSource(fullRecipeDataModel.recipe),
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
