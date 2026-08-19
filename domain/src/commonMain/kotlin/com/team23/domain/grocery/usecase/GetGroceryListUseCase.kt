package com.team23.domain.grocery.usecase

import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.domain.recipe.model.IngredientDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetGroceryListUseCase(
    private val groceryListRepository: GroceryListRepository
) {
    fun invoke(): Flow<GroceryDomainModel> = groceryListRepository.getGroceryListRecipes().map { groceryRecipes ->
        val ingredients = groceryRecipes
            .flatMap { recipe -> applyServingsToIngredients(recipe) }
            .mergeIngredients()
            .sortedBy { it.label.uppercase() }
            .map { ingredient ->
                GroceryDomainModel.Ingredient(ingredientDomainModel = ingredient)
            }

        GroceryDomainModel(recipes = groceryRecipes, ingredients = ingredients)
    }

    private fun applyServingsToIngredients(
        groceryRecipe: GroceryDomainModel.Recipe,
    ): List<IngredientDomainModel> {
        val factor = groceryRecipe.servingsAmount.toFloat() / groceryRecipe.recipeDomainModel.servingsNumber.toFloat()
        return groceryRecipe.recipeDomainModel.ingredients.map { ingredient ->
            when (ingredient) {
                is IngredientDomainModel.WithoutQuantity -> ingredient
                is IngredientDomainModel.WithQuantity.WithoutUnit -> ingredient.copy(
                    quantity = ingredient.quantity * factor
                )
                is IngredientDomainModel.WithQuantity.WithUnit -> ingredient.copy(
                    quantity = ingredient.quantity * factor
                )
            }
        }
    }

    private fun List<IngredientDomainModel>.mergeIngredients(): List<IngredientDomainModel> {
        val result = mutableListOf<IngredientDomainModel>()

        for (ingredient in this) {
            when (ingredient) {
                is IngredientDomainModel.WithoutQuantity -> {
                    if (result.none { ingredient.shouldMergeWith(it) }) {
                        result += ingredient
                    }
                }

                is IngredientDomainModel.WithQuantity.WithoutUnit -> {
                    val index = result.indexOfFirst { ingredient.shouldMergeWith(it) }

                    if (index == -1) {
                        result += ingredient
                    } else {
                        val existing = result[index] as IngredientDomainModel.WithQuantity.WithoutUnit

                        result[index] = existing.copy(quantity = existing.quantity + ingredient.quantity)
                    }
                }

                is IngredientDomainModel.WithQuantity.WithUnit -> {
                    val index = result.indexOfFirst { ingredient.shouldMergeWith(it) }

                    if (index == -1) {
                        result += ingredient
                    } else {
                        val existing = result[index] as IngredientDomainModel.WithQuantity.WithUnit

                        result[index] = existing.copy(quantity = existing.quantity + ingredient.quantity)
                    }
                }
            }
        }

        return result
    }
}
