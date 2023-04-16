package com.example.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

data class FullRecipeDataModel(
	@Embedded val recipe: RecipeDataModel,
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

@Entity
data class RecipeDataModel(
	@PrimaryKey val href: String,
	val imgSrc: String,
	val title: String,
	val subTitle: String,
	val instructionTitle: String,
	val lastTitle: String,
)
