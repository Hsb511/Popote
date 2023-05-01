package com.team23.presentation.home.mappers

import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.domain.models.LanguageDomainModel
import com.team23.domain.models.RecipeDomainModel
import com.team23.presentation.home.models.HomeRecipeUiModel
import javax.inject.Inject

class HomeRecipeMapper @Inject constructor() {
    fun toUiModel(summarizedRecipe: RecipeDomainModel.Summarized)= HomeRecipeUiModel(
        id = summarizedRecipe.id,
        title = summarizedRecipe.title,
        imageProperty = NeuracrImageProperty.Remote(
            contentDescription = summarizedRecipe.title,
            url = summarizedRecipe.imageUrl,
        ),
        flagProperty = when(summarizedRecipe.language) {
            LanguageDomainModel.ENGLISH -> NeuracrFlagProperty.UK_US
            LanguageDomainModel.FRENCH -> NeuracrFlagProperty.FRENCH
        }
    )
}
