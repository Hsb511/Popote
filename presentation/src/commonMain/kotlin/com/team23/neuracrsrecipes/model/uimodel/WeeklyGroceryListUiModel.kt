package com.team23.neuracrsrecipes.model.uimodel

data class WeeklyGroceryListUiModel(
    val recipes: List<SummarizedRecipeUiModel>,
    val ingredients: List<IngredientUiModel>,
)
