package com.example.presentation.home.mappers

import com.example.design_system.image.NeuracrImageProperty
import com.example.domain.models.RecipeDomainModel
import com.example.presentation.home.models.HomeRecipeUiModel
import javax.inject.Inject

class HomeRecipeMapper @Inject constructor() {
    fun toUiModel(summarizedRecipe: RecipeDomainModel.Summarized)= HomeRecipeUiModel(
        id = summarizedRecipe.id,
        title = summarizedRecipe.title,
        imageProperty = NeuracrImageProperty.Remote(
            contentDescription = summarizedRecipe.title,
            url = summarizedRecipe.imageUrl,
        )
    )
}
