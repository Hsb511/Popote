package com.example.presentation.recipe.models

import com.example.design_system.image.NeuracrImageProperty

data class RecipeUiModel(
	val title: String,
	val date: String,
	val author: String,
	val tags: List<String>,
	val image: NeuracrImageProperty,
	val ingredients: List<IngredientUiModel>,
	val defaultServingsAmount: Int,
	val description: String,
	val conclusion: String,
	// TODO
)
