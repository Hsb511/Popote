package com.team23.domain.recipe.usecase

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetFullRecipeByIdUseCase(
    private val recipeRepository: RecipeRepository,
    private val favoriteRepository: FavoriteRepository,
    private val groceryListRepository: GroceryListRepository,
) {
    fun invoke(recipeId: String): Flow<Result<RecipeDomainModel.Full>> =
        combine(
            favoriteRepository.isFavorite(recipeId),
            groceryListRepository.isInGroceryList(recipeId)
        ) { isFavorite, isInGroceryList ->
            runCatching {
                recipeRepository.loadFullRecipeByIdFromNeuracrIfNeeded(recipeId)
                (recipeRepository.getFullRecipeById(recipeId)?.copy(isFavorite = isFavorite, isInGroceryList = isInGroceryList) ?: throw IllegalArgumentException("Impossible to load full recipe with id $recipeId"))
            }
        }
}
