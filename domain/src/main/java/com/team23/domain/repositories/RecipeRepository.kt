package com.team23.domain.repositories

import com.team23.domain.models.RecipeDomainModel

interface RecipeRepository {
    suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized>
    suspend fun loadAllSummarizedRecipesIfNeeded()
    suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String)
    suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full?
}
