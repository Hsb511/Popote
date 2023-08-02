package com.team23.presentation.recipe.models

sealed class IngredientsUiModel(
	open val ingredients: List<IngredientUiModel>,
	open val currentServingsAmount: String,
	open val onValueChanged: (String) -> Unit,
	open val onAddOneServing: () -> Unit,
	open val onSubtractOneServing: () -> Unit,
) {
	data class FromRecipeScreen(
		override val ingredients: List<IngredientUiModel>,
		override val currentServingsAmount: String,
		override val onValueChanged: (String) -> Unit,
		override val onAddOneServing: () -> Unit,
		override val onSubtractOneServing: () -> Unit,
	): IngredientsUiModel(ingredients, currentServingsAmount, onValueChanged, onAddOneServing, onSubtractOneServing)

	data class FromAddScreen(
		override val ingredients: List<IngredientUiModel>,
		override val currentServingsAmount: String,
		override val onValueChanged: (String) -> Unit,
		override val onAddOneServing: () -> Unit,
		override val onSubtractOneServing: () -> Unit,
		val onAddIngredient: () -> Unit,
		val onDeleteIngredient: (Int) -> Unit,
		val onUpdateIngredient: (IngredientUiModel, Int) -> Unit,
	): IngredientsUiModel(ingredients, currentServingsAmount, onValueChanged, onAddOneServing, onSubtractOneServing)

}