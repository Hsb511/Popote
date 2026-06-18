package com.team23.neuracrsrecipes.model.uimodel

import androidx.compose.runtime.Immutable
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.property.ImageProperty

@Immutable
data class SummarizedRecipeUiModel(
    val id: String,
    val title: String,
    val imageProperty: ImageProperty,
    val cuisineFlag: FlagProperty?,
    val languageFlag: FlagProperty,
    val isFavorite: Boolean,
    val isLocallySaved: Boolean,
)
