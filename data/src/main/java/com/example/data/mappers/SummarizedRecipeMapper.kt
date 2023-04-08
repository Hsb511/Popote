package com.example.data.mappers

import com.example.data.datasources.NeuracrWebsiteDataSource.Companion.NEURACR_WEBSITE_HOME_URL
import com.example.data.models.SummarizedRecipeDataModel
import com.example.domain.models.LanguageDomainModel
import com.example.domain.models.RecipeDomainModel
import javax.inject.Inject

class SummarizedRecipeMapper @Inject constructor(
    private val dateMapper: DateMapper,
) {
    fun toSummarizedRecipeDomainModels(summarizedRecipeDataModels: List<SummarizedRecipeDataModel>)
        : List<RecipeDomainModel.Summarized> = summarizedRecipeDataModels.map(::toSummarizedRecipeDomainModel)

    private fun toSummarizedRecipeDomainModel(summarizedRecipeDataModel: SummarizedRecipeDataModel) =
        RecipeDomainModel.Summarized(
            id = summarizedRecipeDataModel.href,
            title = summarizedRecipeDataModel.title,
            imageUrl = "$NEURACR_WEBSITE_HOME_URL${summarizedRecipeDataModel.imgSrc}",
            date = dateMapper.toLocalDate(dateStr = summarizedRecipeDataModel.href
                .split("/recipes/")[1]
                .split("/")
                .let { splitData -> "${splitData[0]}/${splitData[1]}/${splitData[2]}" }
            ),
            language = if (summarizedRecipeDataModel.href.split(".html")[0].endsWith("_fr")) {
                LanguageDomainModel.FRENCH
            } else {
                LanguageDomainModel.ENGLISH
            }
        )
}
