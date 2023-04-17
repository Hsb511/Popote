package com.example.presentation.recipe.mappers

import com.example.domain.models.IngredientDomainModel
import javax.inject.Inject
import kotlin.math.roundToInt

class IngredientMapper @Inject constructor(){
	fun toStringList(ingredients: List<IngredientDomainModel>): List<String> =
		ingredients.map(::toString)

	private fun toString(ingredient: IngredientDomainModel): String = when(ingredient) {
		is IngredientDomainModel.WithQuantity.WithUnit -> with(ingredient) {
			"${quantity.roundToInt()} $unit - $label"
		}
		is IngredientDomainModel.WithQuantity.WithoutUnit -> with(ingredient) {
			"${quantity.roundToInt()} - $label"
		}
		is IngredientDomainModel.WithoutQuantity -> ingredient.label
	}
}