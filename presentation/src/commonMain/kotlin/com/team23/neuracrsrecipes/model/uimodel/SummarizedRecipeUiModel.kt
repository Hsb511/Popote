package com.team23.neuracrsrecipes.model.uimodel

import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty

data class SummarizedRecipeUiModel(
    val id: String,
    val title: String,
    val imageProperty: ImageProperty,
    val flagProperty: FlagProperty,
    val isFavorite: Boolean,
    val isLocallySaved: Boolean,
)
