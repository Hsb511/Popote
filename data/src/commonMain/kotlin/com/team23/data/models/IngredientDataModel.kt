package com.team23.data.models

data class IngredientDataModel(
	val id: Long = 0L,
	val recipeId: String,
	val label: String,
	val quantity: String?,
	val unit: String?,
)
