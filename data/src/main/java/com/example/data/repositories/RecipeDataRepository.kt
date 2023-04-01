package com.example.data.repositories

import com.example.data.daos.RecipeDao
import com.example.data.datasources.NeuracrWebsiteDataSource
import com.example.data.mappers.RawElementsMapper
import com.example.data.mappers.SummarizedRecipeMapper
import com.example.domain.models.RecipeDomainModel
import com.example.domain.repositories.RecipeRepository
import org.jsoup.select.Elements
import javax.inject.Inject

internal class RecipeDataRepository @Inject constructor(
    private val recipeDao: RecipeDao,
    private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource,
    private val rawElementsMapper: RawElementsMapper,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
) : RecipeRepository {
    override suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized> {
        val summarizedRecipeDataModels = recipeDao.getAll()
        return summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipeDataModels)
    }

    override suspend fun loadAllSummarizedRecipesIfNeeded() {
        if (recipeDao.getAll().isEmpty()) {
            val rawLatestPosts: Elements = neuracrWebsiteDataSource.getLatestPostsFromHome()
            val summarizedRecipeDataModels = rawElementsMapper.toSummarizedRecipeDataModels(rawLatestPosts)
            recipeDao.insertAll(*summarizedRecipeDataModels.toTypedArray())
        }
    }

    override suspend fun getFullRecipeById(id: String): RecipeDomainModel.Full? {
        // TODO GET FROM LOCAL VIA ROOM
        return null
    }
}
