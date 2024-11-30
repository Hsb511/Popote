package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.neuracrsrecipes.extension.toReadableQuantity
import com.team23.neuracrsrecipes.model.uimodel.IngredientUiModel

class IngredientUiMapper {

	fun toIngredientUiModels(ingredients: List<IngredientDomainModel>): List<IngredientUiModel> =
		ingredients.map(::toIngredientUiModel)

	fun toIngredientDomainModels(ingredients: List<IngredientUiModel>): List<IngredientDomainModel> =
		ingredients.map(::toIngredientDomainModel)

	private fun toIngredientUiModel(ingredient: IngredientDomainModel): IngredientUiModel = when (ingredient) {
		is IngredientDomainModel.WithQuantity.WithUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantity.toReadableQuantity(), unit = unit, label = label)
		}
		is IngredientDomainModel.WithQuantity.WithoutUnit -> with(ingredient) {
			IngredientUiModel(quantity = quantity.toReadableQuantity(), unit = null, label = label)
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
