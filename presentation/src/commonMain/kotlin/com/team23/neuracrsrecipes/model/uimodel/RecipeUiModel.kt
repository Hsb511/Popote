package com.team23.neuracrsrecipes.model.uimodel

import com.team23.neuracrsrecipes.model.property.ImageProperty

data class RecipeUiModel(
    val id: String,
    val title: String,
    val date: String,
    val author: String,
    val tags: List<String>,
    val image: ImageProperty,
    val ingredients: List<IngredientUiModel>,
    val instructions: List<InstructionUiModel>,
    val defaultServingsAmount: Int,
    val description: String,
    val conclusion: String,
    val isFavorite: Boolean,
    val isLocallySaved: Boolean,
)
