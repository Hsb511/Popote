package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel.Source
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel

class SummarizedRecipeUiMapper(
    private val imageUiMapper: ImageUiMapper,
) {

    fun toUiModels(summarizedRecipes: List<RecipeDomainModel.Summarized>) =
        summarizedRecipes.map(::toUiModel)

    fun toUiModel(summarizedRecipe: RecipeDomainModel.Summarized) = with(summarizedRecipe) {
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
        )
    }
}
