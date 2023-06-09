package com.team23.data.mappers

import com.team23.data.models.IngredientDataModel
import com.team23.domain.models.IngredientDomainModel
import javax.inject.Inject

class IngredientMapper @Inject constructor() {
	fun toIngredientListDomainModel(ingredientsDataModel: List<IngredientDataModel>): List<IngredientDomainModel> =
		ingredientsDataModel.map(::toIngredientDomainModel)

	private fun toIngredientDomainModel(ingredientDataModel: IngredientDataModel) = when {
		!ingredientDataModel.unit.isNullOrEmpty() && !ingredientDataModel.quantity.isNullOrEmpty() ->
			IngredientDomainModel.WithQuantity.WithUnit(
				label = ingredientDataModel.label,
				quantity = ingredientDataModel.quantity.toFloat(),
				unit = ingredientDataModel.unit
			)
		!ingredientDataModel.quantity.isNullOrEmpty() -> IngredientDomainModel.WithQuantity.WithoutUnit(
			label = ingredientDataModel.label,
			quantity = ingredientDataModel.quantity.toFloat(),
		)
		else -> IngredientDomainModel.WithoutQuantity(
			ingredientDataModel.label,
		)
	}
}
