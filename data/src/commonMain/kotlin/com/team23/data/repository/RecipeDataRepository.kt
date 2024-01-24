package com.team23.data.repository

import com.team23.data.datasource.NeuracrWebsiteDataSource
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow

internal class RecipeDataRepository(
    private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource,
    private val summarizedRecipeParser: SummarizedRecipeParser,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
): RecipeRepository {
    override suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized> {
        val recipesElements = neuracrWebsiteDataSource.getLatestPostsFromHome()
        val recipeDataModels = summarizedRecipeParser.toSummarizedRecipeDataModels(recipesElements)
        return summarizedRecipeMapper.toSummarizedRecipeDomainModels(recipeDataModels)
    }

    override suspend fun getCountSummarizedRecipes(): Int {
        TODO("Not yet implemented")
    }

    override suspend fun loadAllSummarizedRecipesIfNeeded() {
        TODO("Not yet implemented")
    }

    override suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full? {
        TODO("Not yet implemented")
    }

    override fun getSummarizedRecipesBySearchText(searchText: String): Flow<List<RecipeDomainModel.Summarized>> {
        TODO("Not yet implemented")
    }

    override suspend fun updateRecipe(recipe: RecipeDomainModel.Full) {
        TODO("Not yet implemented")
    }

    override suspend fun saveRecipe(recipeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun setRecipeBackToTemp(recipeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteRecipe(recipeId: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getAllAuthorsName(): List<String> {
        TODO("Not yet implemented")
    }
}