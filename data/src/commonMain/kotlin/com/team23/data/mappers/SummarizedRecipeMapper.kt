package com.team23.data.mappers

import com.team23.data.models.SummarizedRecipeDataModel
import com.team23.domain.recipe.model.RecipeDomainModel

class SummarizedRecipeMapper(
	private val imageMapper: ImageMapper,
	private val dateMapper: DateMapper,
	private val languageMapper: LanguageMapper,
) {
	fun toSummarizedRecipeDomainModels(summarizedRecipeDataModels: List<SummarizedRecipeDataModel>)
		: List<RecipeDomainModel.Summarized> = summarizedRecipeDataModels.map(::toSummarizedRecipeDomainModel)

	private fun toSummarizedRecipeDomainModel(summarizedRecipeDataModel: SummarizedRecipeDataModel) =
		RecipeDomainModel.Summarized(
			id = summarizedRecipeDataModel.href,
			title = summarizedRecipeDataModel.title,
			imageUrl = imageMapper.toImageUrl(summarizedRecipeDataModel.imgSrc),
			date = dateMapper.toLocalDateFromHrefDate(dateStr = summarizedRecipeDataModel.href
				.split("/recipes/")[1]
				.split("/")
				.let { splitData -> "${splitData[0]}/${splitData[1]}/${splitData[2]}" }
			),
			language = languageMapper.toLanguageDomainModel(summarizedRecipeDataModel.href),
			isFavorite = false,
			source = RecipeDomainModel.Source.Remote,
		)

	fun toSummarizedRecipeDataModel(recipe: RecipeDomainModel.Summarized): SummarizedRecipeDataModel =
		SummarizedRecipeDataModel(
			href = recipe.id,
			imgSrc = recipe.imageUrl,
			title = recipe.title,
		)
}
