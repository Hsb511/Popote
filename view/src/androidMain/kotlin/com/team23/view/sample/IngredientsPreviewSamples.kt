package com.team23.view.sample

import com.team23.neuracrsrecipes.model.uimodel.IngredientUiModel
import com.team23.neuracrsrecipes.model.uimodel.IngredientsUiModel

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
