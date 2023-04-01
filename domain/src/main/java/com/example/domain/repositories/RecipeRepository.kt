package com.example.domain.repositories

import com.example.domain.models.RecipeDomainModel

interface RecipeRepository {
    suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized>
    suspend fun loadAllSummarizedRecipesIfNeeded()
    suspend fun getFullRecipeById(id: String): RecipeDomainModel.Full?
}
