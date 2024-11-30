package com.team23.view.sample.uimodel

import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.view.sample.property.resourceImagePreviewSample

val summarizedRecipeSample = SummarizedRecipeUiModel(
    id = "",
    imageProperty = resourceImagePreviewSample,
    title = "Bretzels",
    flagProperty = FlagProperty.FRENCH,
    isFavorite = true,
    isLocallySaved = true,
)