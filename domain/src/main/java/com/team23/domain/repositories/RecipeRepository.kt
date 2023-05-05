package com.team23.domain.repositories

import com.team23.domain.models.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized>
    suspend fun loadAllSummarizedRecipesIfNeeded()
    suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String)
    suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full?
    fun getSummarizedRecipesBySearchTextAndTags(
        searchText: String,
        tagsList: List<String>,
    ): Flow<List<RecipeDomainModel.Summarized>>
}
