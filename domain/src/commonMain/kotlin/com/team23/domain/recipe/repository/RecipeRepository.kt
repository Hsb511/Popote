package com.team23.domain.recipe.repository

import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized>
    suspend fun getCountSummarizedRecipes(): Int
    suspend fun loadAllSummarizedRecipesIfNeeded()
    suspend fun loadAllSummarizedRecipes()
    suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String)
    suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full?
    fun getSummarizedRecipesBySearchText(searchText: String): Flow<List<RecipeDomainModel.Summarized>>
    suspend fun updateRecipe(recipe: RecipeDomainModel.Full)
    suspend fun saveRecipe(recipeId: String)
    suspend fun setRecipeBackToTemp(recipeId: String)
    suspend fun deleteRecipe(recipeId: String)
    suspend fun getAllAuthorsName(): List<String>
}
