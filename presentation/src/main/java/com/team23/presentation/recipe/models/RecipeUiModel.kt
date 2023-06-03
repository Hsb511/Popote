package com.team23.presentation.recipe.models

import com.team23.design_system.image.NeuracrImageProperty

data class RecipeUiModel(
	val id: String,
	val title: String,
	val date: String,
	val author: String,
	val tags: List<String>,
	val image: NeuracrImageProperty,
	val ingredients: List<IngredientUiModel>,
	val instructions: List<InstructionUiModel>,
	val defaultServingsAmount: Int,
	val description: String,
	val conclusion: String,
	val isFavorite: Boolean,
)
