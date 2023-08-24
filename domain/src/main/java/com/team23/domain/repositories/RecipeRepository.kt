package com.team23.domain.repositories

import com.team23.domain.models.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized>
    suspend fun getCountSummarizedRecipes(): Int
    suspend fun loadAllSummarizedRecipesIfNeeded()
    suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String)
    suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full?
    fun getSummarizedRecipesBySearchText(searchText: String): Flow<List<RecipeDomainModel.Summarized>>
    suspend fun updateRecipe(recipe: RecipeDomainModel.Full)
    suspend fun saveRecipe(recipeId: String)
}
