package com.example.data.repositories

import com.example.data.daos.FullRecipeDao
import com.example.data.daos.SummarizedRecipeDao
import com.example.data.datasources.NeuracrWebsiteDataSource
import com.example.data.mappers.FullRecipeMapper
import com.example.data.mappers.RawElementsMapper
import com.example.data.mappers.SummarizedRecipeMapper
import com.example.domain.models.RecipeDomainModel
import com.example.domain.repositories.RecipeRepository
import org.jsoup.select.Elements
import javax.inject.Inject

internal class RecipeDataRepository @Inject constructor(
	private val summarizedRecipeDao: SummarizedRecipeDao,
	private val fullRecipeDao: FullRecipeDao,
	private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource,
	private val rawElementsMapper: RawElementsMapper,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
	private val fullRecipeMapper: FullRecipeMapper,
) : RecipeRepository {
	override suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized> {
		val summarizedRecipeDataModels = summarizedRecipeDao.getAll()
		return summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipeDataModels)
	}

	override suspend fun loadAllSummarizedRecipesIfNeeded() {
		if (summarizedRecipeDao.getAll().isEmpty()) {
			val rawLatestPosts: Elements = neuracrWebsiteDataSource.getLatestPostsFromHome()
			val summarizedRecipeDataModels = rawElementsMapper.toSummarizedRecipeDataModels(rawLatestPosts)
			summarizedRecipeDao.insertAll(*summarizedRecipeDataModels.toTypedArray())
		}
	}

	override suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String) {
		if (fullRecipeDao.findById(recipeId) == null) {
			val rawRecipe: Elements = neuracrWebsiteDataSource.getRecipeById(recipeId)
			val fullRecipeDataModel = rawElementsMapper.toFullRecipeDataModel(recipeId, rawRecipe)
			fullRecipeDao.insertOrReplace(fullRecipeDataModel)
		}
	}

	override suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full? =
		fullRecipeDao.findById(recipeId)?.let { dataModel ->
			fullRecipeMapper.toFullRecipeDomainModel(dataModel)
		}
}
