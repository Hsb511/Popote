package com.team23.data.repository

import com.team23.data.datasource.PopoteLocalDataSource
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.models.FullRecipeDataModel
import com.team23.data.models.GroceryListDataModel
import com.team23.domain.grocery.model.GroceryDomainModel
import com.team23.domain.grocery.repository.GroceryListRepository
import kotlinx.coroutines.flow.Flow
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

    override suspend fun updateGroceryList(recipeId: String): Boolean {
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

    override fun getGroceryList(): Flow<GroceryDomainModel> =
        groceryListDao.getAll().map { groceryItems ->
            val recipes = groceryItems.mapNotNull { item ->
                buildFullRecipeDomainModel(item.recipeId)?.let { fullRecipe ->
                    GroceryDomainModel.Recipe(
                        recipeDomainModel = fullRecipe,
                        servingsAmount = item.servingsAmount,
                    )
                }
            }
            val ingredients = recipes.flatMap { recipe ->
                recipe.recipeDomainModel.ingredients.map { ingredient ->
                    GroceryDomainModel.Ingredient(ingredientDomainModel = ingredient)
                }
            }
            GroceryDomainModel(recipes = recipes, ingredients = ingredients)
        }

    override suspend fun clearAllGroceryList() {
        groceryListDao.deleteAll()
    }

    private fun buildFullRecipeDomainModel(recipeId: String) =
        baseRecipeDao.findBaseRecipeById(recipeId)?.let { baseRecipe ->
            val fullRecipeDataModel = FullRecipeDataModel(
                recipe = baseRecipe,
                tags = tagDao.getTagsByRecipeId(recipeId),
                ingredients = ingredientDao.getAllByRecipeId(recipeId),
                instructions = instructionDao.getAllByRecipeId(recipeId),
            )
            fullRecipeMapper.toFullRecipeDomainModel(fullRecipeDataModel).copy(
                isFavorite = favoriteDao.isStored(recipeId),
                isInGroceryList = true,
            )
        }
}

