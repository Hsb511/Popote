package com.team23.domain.favorite.usecase

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.grocery.repository.GroceryListRepository
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.tag.repository.TagRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.transformLatest

class GetAllFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository,
    private val groceryListRepository: GroceryListRepository,
    private val tagRepository: TagRepository,
) {

    @OptIn(ExperimentalCoroutinesApi::class)
    fun invoke(): Flow<List<RecipeDomainModel.Summarized>> = favoriteRepository.getAllFavorites()
        .transformLatest { favoriteRecipes ->
            emit(favoriteRecipes)

            val enrichedRecipes = favoriteRecipes.map { recipe ->
                val cuisineRegion = tagRepository.getCuisineRegion(recipe.id)
                val isInGroceryList = groceryListRepository.isInGroceryList(recipe.id).firstOrNull() ?: false
                recipe.copy(cuisineRegion = cuisineRegion, isInGroceryList = isInGroceryList)
            }

            emit(enrichedRecipes)
        }
}
