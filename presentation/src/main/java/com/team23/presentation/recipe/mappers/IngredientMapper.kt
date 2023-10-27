package com.team23.presentation.recipe.mappers

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.presentation.recipe.models.IngredientUiModel
import javax.inject.Inject

class IngredientMapper @Inject constructor(
	private val quantityMapper: QuantityMapper,
) {
	fun toIngredientUiModels(ingredients: List<IngredientDomainModel>): List<IngredientUiModel> =
		ingredients.map(::toIngredientUiModel)

	fun toIngredientDomainModels(ingredients: List<IngredientUiModel>): List<IngredientDomainModel> =
		ingredients.map(::toIngredientDomainModel)

	private fun toIngredientUiModel(ingredient: IngredientDomainModel): IngredientUiModel = when (ingredient) {
		is IngredientDomainModel.WithQuantity.WithUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantityMapper.toString(quantity), unit = unit, label = label)
		}
		is IngredientDomainModel.WithQuantity.WithoutUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantityMapper.toString(quantity), unit = null, label = label)
		}
		is IngredientDomainModel.WithoutQuantity -> IngredientUiModel(quantity = null, unit = null, label = ingredient.label)
	}

	private fun toIngredientDomainModel(ingredient: IngredientUiModel): IngredientDomainModel {
		val quantity = ingredient.quantity?.toFloatOrNull()
		return when {
			ingredient.unit != null && quantity != null -> IngredientDomainModel.WithQuantity.WithUnit(
				label = ingredient.label,
				quantity = quantity,
				unit = ingredient.unit,
			)
			quantity != null -> IngredientDomainModel.WithQuantity.WithoutUnit(
				label = ingredient.label,
				quantity = quantity,
			)
			else -> IngredientDomainModel.WithoutQuantity(label = ingredient.label)
		}
	}

}
