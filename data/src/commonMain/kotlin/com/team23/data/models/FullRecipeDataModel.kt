package com.team23.data.models

data class FullRecipeDataModel(
	val recipe: BaseRecipeDataModel,
	val tags: List<TagDataModel>,
	val ingredients: List<IngredientDataModel>,
	val instructions: List<InstructionDataModel>,
)
