package com.team23.neuracrsrecipes.model.action

import com.team23.neuracrsrecipes.model.uimodel.GroceryListUiModel

sealed interface GroceryListAction {
    data object Clear: GroceryListAction
    data class ToggleIngredient(
        val ingredient: GroceryListUiModel.Ingredient,
    ): GroceryListAction
    data class ChangeServingsAmount(
        val recipeId: String,
        val newServingsAmount: Int,
    ): GroceryListAction
    data class OnRecipeClick(
        val recipeId: String,
    ): GroceryListAction
}
