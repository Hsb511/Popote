package com.team23.data.repository

import com.team23.data.datasource.NeuracrLocalDataSource
import com.team23.data.datasource.NeuracrWebsiteDataSource
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.models.FullRecipeDataModel
import com.team23.data.parsers.FullRecipeParser
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RecipeDataRepository(
    neuracrLocalDataSource: NeuracrLocalDataSource,
    private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource,
    private val summarizedRecipeParser: SummarizedRecipeParser,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
    private val fullRecipeMapper: FullRecipeMapper,
    private val fullRecipeParser: FullRecipeParser,
) : RecipeRepository {
    private val summarizedRecipeDao = neuracrLocalDataSource.summarizedRecipeDao
    private val favoriteDao = neuracrLocalDataSource.favoriteDao

    override suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized> {
        val summarizedRecipeDataModels = summarizedRecipeDao.getAll()
        val summarizedRecipeDomainModels =
            summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipeDataModels)
        return summarizedRecipeDomainModels.map(::enrichWithFavorite)
    }

    override suspend fun getCountSummarizedRecipes(): Int = summarizedRecipeDao.getCount().toInt()

    override suspend fun loadAllSummarizedRecipesIfNeeded() {
        if (getCountSummarizedRecipes() == 0) {
            val recipesElements = neuracrWebsiteDataSource.getLatestPostsFromHome()
            val recipeDataModels =
                summarizedRecipeParser.toSummarizedRecipeDataModels(recipesElements)
            summarizedRecipeDao.insertAll(*recipeDataModels.toTypedArray())
        }
    }

    override suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String) {
        // TODO "Not yet implemented"
    }

    override suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full? {
        val rawRecipe = neuracrWebsiteDataSource.getRecipeById(recipeId)
        val fullRecipeDataModel = fullRecipeParser.toFullRecipeDataModel(recipeId, rawRecipe)
        return fullRecipeMapper.toFullRecipeDomainModel(fullRecipeDataModel)
    }

    override fun getSummarizedRecipesBySearchText(searchText: String): Flow<List<RecipeDomainModel.Summarized>> =
        summarizedRecipeDao.searchBaseRecipeByTitle(searchText)
            .map(summarizedRecipeMapper::toSummarizedRecipeDomainModels)
            .map { it.map(::enrichWithFavorite) }


    override suspend fun updateRecipe(recipe: RecipeDomainModel.Full) {
        val fullRecipeDataModel = fullRecipeMapper.toFullRecipeDataModel(recipe)
        val recipeId = fullRecipeDataModel.recipe.href

        deleteDataByRecipeId(recipeId)
        insertOrReplaceFullRecipe(fullRecipeDataModel)
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

    private suspend fun insertOrReplaceFullRecipe(fullRecipeDataModel: FullRecipeDataModel) {

    }

    private suspend fun deleteDataByRecipeId(recipeId: String) {
        summarizedRecipeDao.deleteByRecipeId(recipeId)
    }

    private fun enrichWithFavorite(recipe: RecipeDomainModel.Summarized) =
        recipe.copy(isFavorite = favoriteDao.isStored(recipe.id))
}
