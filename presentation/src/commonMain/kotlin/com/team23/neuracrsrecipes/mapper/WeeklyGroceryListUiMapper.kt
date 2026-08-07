package com.team23.neuracrsrecipes.mapper

import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.neuracrsrecipes.model.uimodel.WeeklyGroceryListUiModel

class WeeklyGroceryListUiMapper(
    val fullRecipeUiMapper: RecipeUiMapper,
    val ingredientUiMapper: IngredientUiMapper,
) {

    fun toUiModel(groceryList: GroceryDomainModel): WeeklyGroceryListUiModel {
        return WeeklyGroceryListUiModel(
            recipes = groceryList.recipes.map(::toGroceryRecipeUiModel),
            ingredients = groceryList.ingredients.map(::toGroceryIngredientUiModel)
        )
    }

    fun toGroceryRecipeUiModel(recipe: GroceryDomainModel.Recipe): WeeklyGroceryListUiModel.Recipe {
        return WeeklyGroceryListUiModel.Recipe(
            uiModel = fullRecipeUiMapper.toSummarizedRecipeUiModel(recipe.recipeDomainModel),
            servingsAmount = recipe.servingsAmount,
        )
    }

    fun toGroceryIngredientUiModel(ingredient: GroceryDomainModel.Ingredient): WeeklyGroceryListUiModel.Ingredient {
        return WeeklyGroceryListUiModel.Ingredient(
            uiModel = ingredientUiMapper.toIngredientUiModel(ingredient.ingredientDomainModel),
            isChecked = ingredient.isSelected,
        )
    }
}
