package com.team23.data.models

data class InstructionDataModel(
	val id: Long = 0L,
	val recipeId: String,
	val label: String,
	val order: Int,
)
