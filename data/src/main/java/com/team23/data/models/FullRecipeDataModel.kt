package com.team23.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class FullRecipeDataModel(
	@Embedded val recipe: BaseRecipeDataModel,
	@Relation(
		parentColumn = "href",
		entityColumn = "recipeId"
	)
	val tags: List<TagDataModel>,
	@Relation(
		parentColumn = "href",
		entityColumn = "recipeId"
	)
	val ingredients: List<IngredientDataModel>,
	@Relation(
		parentColumn = "href",
		entityColumn = "recipeId"
	)
	val instructions: List<InstructionDataModel>,
)
