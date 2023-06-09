package com.team23.data.repositories

import com.team23.data.daos.FavoriteDao
import com.team23.data.daos.IngredientDao
import com.team23.data.daos.InstructionDao
import com.team23.data.daos.RecipeDao
import com.team23.data.daos.SummarizedRecipeDao
import com.team23.data.daos.TagDao
import com.team23.data.datasources.NeuracrWebsiteDataSource
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.parsers.FullRecipeParser
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.jsoup.select.Elements
import javax.inject.Inject

internal class RecipeRepositoryImpl @Inject constructor(
	private val summarizedRecipeDao: SummarizedRecipeDao,
	private val recipeDao: RecipeDao,
	private val tagDao: TagDao,
	private val ingredientDao: IngredientDao,
	private val instructionDao: InstructionDao,
	private val favoriteDao: FavoriteDao,
	private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource,
	private val summarizedRecipeParser: SummarizedRecipeParser,
	private val fullRecipeParser: FullRecipeParser,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
	private val fullRecipeMapper: FullRecipeMapper,
) : RecipeRepository {
	override suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized> {
		val summarizedRecipeDataModels = summarizedRecipeDao.getAll()
		return summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipeDataModels).map { recipe ->
			recipe.copy(isFavorite = favoriteDao.isStored(recipe.id))
		}
	}

	override suspend fun getCountSummarizedRecipes(): Int = summarizedRecipeDao.getCount()

	override suspend fun loadAllSummarizedRecipesIfNeeded() {
		if (summarizedRecipeDao.getAll().isEmpty()) {
			val rawLatestPosts: Elements = neuracrWebsiteDataSource.getLatestPostsFromHome()
			val summarizedRecipeDataModels = summarizedRecipeParser.toSummarizedRecipeDataModels(rawLatestPosts)
			summarizedRecipeDao.insertAll(*summarizedRecipeDataModels.toTypedArray())
		}
	}

	override suspend fun loadFullRecipeByIdFromNeuracrIfNeeded(recipeId: String) {
		if (recipeDao.findFullRecipeById(recipeId) == null) {
			val rawRecipe: Elements = neuracrWebsiteDataSource.getRecipeById(recipeId)
			val fullRecipeDataModel = fullRecipeParser.toFullRecipeDataModel(recipeId, rawRecipe)
			recipeDao.insertOrReplace(fullRecipeDataModel.recipe)
			tagDao.insertOrReplace(*fullRecipeDataModel.tags.toTypedArray())
			ingredientDao.insertOrReplace(*fullRecipeDataModel.ingredients.toTypedArray())
			instructionDao.insertOrReplace(*fullRecipeDataModel.instructions.toTypedArray())
		}
	}

	override suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full? =
		recipeDao.findFullRecipeById(recipeId)?.let { dataModel ->
			fullRecipeMapper.toFullRecipeDomainModel(dataModel).copy(
				isFavorite = favoriteDao.isStored(recipeId)
			)
		}

	override fun getSummarizedRecipesBySearchText(searchText: String): Flow<List<RecipeDomainModel.Summarized>> =
		summarizedRecipeDao.searchBaseRecipeByTitle(searchText).map {
			summarizedRecipeMapper.toSummarizedRecipeDomainModels(it).map { recipe ->
				recipe.copy(isFavorite = favoriteDao.isStored(recipe.id))
			}
		}
}
