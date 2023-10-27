package com.team23.data.mappers

import com.team23.data.models.IngredientDataModel
import com.team23.domain.recipe.model.IngredientDomainModel
import javax.inject.Inject

class IngredientMapper @Inject constructor() {
	fun toIngredientListDomainModel(ingredientsDataModel: List<IngredientDataModel>): List<IngredientDomainModel> =
		ingredientsDataModel.map(::toIngredientDomainModel)

	fun toIngredientListDataModel(recipeId: String, ingredients: List<IngredientDomainModel>): List<IngredientDataModel> =
		ingredients.map { ingredient -> toIngredientDataModel(recipeId, ingredient)}

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

	private fun toIngredientDataModel(recipeId: String, ingredient: IngredientDomainModel) = when(ingredient) {
		is IngredientDomainModel.WithQuantity.WithUnit -> IngredientDataModel(
			recipeId = recipeId,
			label = ingredient.label,
			quantity = ingredient.quantity.toString(),
			unit = ingredient.unit,
		)
		is IngredientDomainModel.WithQuantity.WithoutUnit -> IngredientDataModel(
			recipeId = recipeId,
			label = ingredient.label,
			quantity = ingredient.quantity.toString(),
			unit = null,
		)
		is IngredientDomainModel.WithoutQuantity -> IngredientDataModel(
			recipeId = recipeId,
			label = ingredient.label,
			quantity = null,
			unit = null,
		)
	}
}
