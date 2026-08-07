package com.team23.neuracrsrecipes.model.action

import com.team23.neuracrsrecipes.model.uimodel.WeeklyGroceryListUiModel

sealed interface WeeklyGroceryListAction {
    data object Clear: WeeklyGroceryListAction
    data class ToggleIngredient(
        val ingredient: WeeklyGroceryListUiModel.Ingredient,
    ): WeeklyGroceryListAction
    data class ChangeServingsAmount(
        val recipeId: String,
        val newServingsAmount: Int,
    ): WeeklyGroceryListAction
    data class OnRecipeClick(
        val recipeId: String,
    ): WeeklyGroceryListAction
}
