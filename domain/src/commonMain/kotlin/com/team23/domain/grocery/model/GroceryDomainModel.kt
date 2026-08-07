package com.team23.domain.grocery.model

import com.team23.domain.recipe.model.IngredientDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel

data class GroceryDomainModel(
    val recipes: List<Recipe> = emptyList(),
    val ingredients: List<Ingredient> = emptyList(),
) {

    data class Recipe(
        val recipeDomainModel: RecipeDomainModel.Full,
        val servingsAmount: Int = recipeDomainModel.servingsNumber,
    )

    data class Ingredient(
        val ingredientDomainModel: IngredientDomainModel,
        val isSelected: Boolean = true,
    )
}
