package com.team23.presentation.recipe.mappers

import com.team23.domain.models.IngredientDomainModel
import com.team23.presentation.recipe.models.IngredientUiModel
import javax.inject.Inject

class IngredientMapper @Inject constructor(
	private val quantityMapper: QuantityMapper,
) {
	fun toIngredientUiModels(ingredients: List<IngredientDomainModel>): List<IngredientUiModel> =
		ingredients.map(this::toIngredientUiModel)

	private fun toIngredientUiModel(ingredient: IngredientDomainModel): IngredientUiModel = when (ingredient) {
		is IngredientDomainModel.WithQuantity.WithUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantityMapper.toString(quantity), unit = unit, label = label)
		}
		is IngredientDomainModel.WithQuantity.WithoutUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantityMapper.toString(quantity), unit = null, label = label)
		}
		is IngredientDomainModel.WithoutQuantity -> IngredientUiModel(quantity = null, unit = null, label = ingredient.label)
	}
}
