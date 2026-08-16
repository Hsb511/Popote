package com.team23.neuracrsrecipes.model.uimodel

import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale

data class GroceryListUiModel(
    val recipes: List<Recipe> = emptyList(),
    val ingredients: List<Ingredient> = emptyList(),
) {
    val isEmpty: Boolean
        get() = recipes.isEmpty() && ingredients.isEmpty()


    data class Recipe(
        val uiModel: SummarizedRecipeUiModel,
        val servingsAmount: Int,
    ) {
        val id: String
            get() = uiModel.id
    }

    data class Ingredient(
        val uiModel: IngredientUiModel,
        val isChecked: Boolean = true,
    ) {
        val id: String
            get() = "${uiModel.label}_${uiModel.quantity}_${uiModel.unit}"

        val displayMainLabel: String = uiModel.label.capitalize(Locale.current)
        val displaySecondaryLabel: String = "${uiModel.quantity.orEmpty()} ${uiModel.unit.orEmpty()}".trim()
    }
}
