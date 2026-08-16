package com.team23.neuracrsrecipes.mapper

import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.neuracrsrecipes.model.uimodel.GroceryListUiModel

class GroceryListUiMapper(
    val fullRecipeUiMapper: RecipeUiMapper,
    val ingredientUiMapper: IngredientUiMapper,
) {

    fun toUiModel(groceryList: GroceryDomainModel): GroceryListUiModel {
        return GroceryListUiModel(
            recipes = groceryList.recipes.map(::toGroceryRecipeUiModel),
            ingredients = groceryList.ingredients.map(::toGroceryIngredientUiModel),
            isLoading = false,
        )
    }

    fun toGroceryRecipeUiModel(recipe: GroceryDomainModel.Recipe): GroceryListUiModel.Recipe {
        return GroceryListUiModel.Recipe(
            uiModel = fullRecipeUiMapper.toSummarizedRecipeUiModel(recipe.recipeDomainModel),
            servingsAmount = recipe.servingsAmount,
        )
    }

    fun toGroceryIngredientUiModel(ingredient: GroceryDomainModel.Ingredient): GroceryListUiModel.Ingredient {
        return GroceryListUiModel.Ingredient(
            uiModel = ingredientUiMapper.toIngredientUiModel(ingredient.ingredientDomainModel),
            isChecked = ingredient.isSelected,
        )
    }
}
