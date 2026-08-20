package com.team23.data.repository

import com.team23.data.datasource.PopoteLocalDataSource
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.models.FullRecipeDataModel
import com.team23.data.models.GroceryListDataModel
import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.domain.grocery.repository.GroceryListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map

internal class GroceryListDataRepository(
    popoteLocalDataSource: PopoteLocalDataSource,
    private val fullRecipeMapper: FullRecipeMapper,
) : GroceryListRepository {

    private val groceryListDao = popoteLocalDataSource.groceryListDao
    private val baseRecipeDao = popoteLocalDataSource.baseRecipeDao
    private val tagDao = popoteLocalDataSource.tagDao
    private val ingredientDao = popoteLocalDataSource.ingredientDao
    private val instructionDao = popoteLocalDataSource.instructionDao
    private val favoriteDao = popoteLocalDataSource.favoriteDao

    override suspend fun toggleInGroceryList(recipeId: String): Boolean {
        if (groceryListDao.isStored(recipeId)) {
            groceryListDao.delete(recipeId)
        } else {
            val defaultServings = baseRecipeDao.findBaseRecipeById(recipeId)?.servingsAmount ?: 1
            groceryListDao.insert(
                GroceryListDataModel(recipeId = recipeId, servingsAmount = defaultServings)
            )
        }
        return groceryListDao.isStored(recipeId)
    }

    override fun getGroceryListRecipes(): Flow<List<GroceryDomainModel.Recipe>> =
        combine(groceryListDao.getAll(), favoriteDao.getAllFavorites()) { groceryItems, favoriteRecipeIds ->
            groceryItems.mapNotNull { item ->
                buildFullRecipeDomainModel(item.recipeId, favoriteRecipeIds)?.let { fullRecipe ->
                    GroceryDomainModel.Recipe(
                        recipeDomainModel = fullRecipe,
                        servingsAmount = item.servingsAmount,
                    )
                }
            }
        }

    override suspend fun clearAllGroceryList() {
        groceryListDao.deleteAll()
        groceryListDao.deleteAllExcludedIngredients()
    }

    override fun getExcludedIngredientIds(): Flow<Set<String>> =
        groceryListDao.getAllExcludedIngredientIds().map { it.toSet() }

    override suspend fun toggleExcludedIngredient(ingredientId: String): Boolean {
        if (groceryListDao.isExcludedIngredientStored(ingredientId)) {
            groceryListDao.deleteExcludedIngredient(ingredientId)
        } else {
            groceryListDao.insertExcludedIngredient(ingredientId)
        }
        return groceryListDao.isExcludedIngredientStored(ingredientId)
    }

    override suspend fun updateServings(recipeId: String, servingsAmount: Int) {
        groceryListDao.updateServings(recipeId, servingsAmount)
    }

    override fun isInGroceryList(recipeId: String): Flow<Boolean> = groceryListDao.isStoredFlow(recipeId)

    private fun buildFullRecipeDomainModel(recipeId: String, favoriteRecipeIds: List<String>) =
        baseRecipeDao.findBaseRecipeById(recipeId)?.let { baseRecipe ->
            val fullRecipeDataModel = FullRecipeDataModel(
                recipe = baseRecipe,
                tags = tagDao.getTagsByRecipeId(recipeId),
                ingredients = ingredientDao.getAllByRecipeId(recipeId),
                instructions = instructionDao.getAllByRecipeId(recipeId),
            )
            fullRecipeMapper.toFullRecipeDomainModel(fullRecipeDataModel).copy(
                isFavorite = recipeId in favoriteRecipeIds,
                isInGroceryList = true,
            )
        }
}
