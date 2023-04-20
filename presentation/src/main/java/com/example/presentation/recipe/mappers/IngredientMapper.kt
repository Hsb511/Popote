package com.example.presentation.recipe.mappers

import com.example.domain.models.IngredientDomainModel
import com.example.presentation.recipe.models.IngredientUiModel
import javax.inject.Inject

class IngredientMapper @Inject constructor(
	private val quantityMapper: QuantityMapper,
) {
	fun toIngredientUiModels(ingredients: List<IngredientDomainModel>): List<IngredientUiModel> =
		ingredients.map(this::toIngredientUiModel)

	private fun toIngredientUiModel(ingredient: IngredientDomainModel): IngredientUiModel = when (ingredient) {
		is IngredientDomainModel.WithQuantity.WithUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantityMapper.toString(quantity), label = " $unit - $label")
		}
		is IngredientDomainModel.WithQuantity.WithoutUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantityMapper.toString(quantity), label = label)
		}
		is IngredientDomainModel.WithoutQuantity -> IngredientUiModel(quantity = null, label = ingredient.label)
	}
}
