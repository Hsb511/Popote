package com.team23.presentation.recipe.models

val ingredientsPreviewSamples = listOf(
	IngredientUiModel("0.5", "", "lime"),
	IngredientUiModel("15", "", "sugar syrup"),
	IngredientUiModel("12", "", "raspberry (frozen)"),
	IngredientUiModel("12", "", "mint leaf"),
)

val ingredientsUiModelPreviewSamples = IngredientsUiModel.FromRecipeScreen(
	ingredients = ingredientsPreviewSamples,
	currentServingsAmount = "4",
	onValueChanged = {},
	onAddOneServing = {},
	onSubtractOneServing = {},
)
