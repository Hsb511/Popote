package com.team23.data.repositories

import com.team23.data.daos.FavoriteDao
import com.team23.data.daos.IngredientDao
import com.team23.data.daos.InstructionDao
import com.team23.data.daos.RecipeDao
import com.team23.data.daos.SummarizedRecipeDao
import com.team23.data.daos.TagDao
import com.team23.data.datasources.NeuracrWebsiteDataSource
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.mappers.SourceMapper
import com.team23.data.mappers.SubtitleMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.models.FullRecipeDataModel
import com.team23.data.models.SummarizedRecipeDataModel
import com.team23.data.parsers.FullRecipeParser
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
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
	private val sourceMapper: SourceMapper,
	private val subtitleMapper: SubtitleMapper,
) : RecipeRepository {
	override suspend fun getAllSummarizedRecipes(): List<RecipeDomainModel.Summarized> =
		summarizedRecipeMapper.toSummarizedRecipeDomainModels(
			summarizedRecipeDao.getAll()
		).map { recipe ->
			recipe.copy(
				isFavorite = favoriteDao.isStored(recipe.id),
				source = sourceMapper.toDomainSource(recipeDao.findFullRecipeById(recipe.id)?.recipe)
			)
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
			insertOrReplaceFullRecipe(fullRecipeDataModel)
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

	override suspend fun updateRecipe(recipe: RecipeDomainModel.Full) {
		val fullRecipeDataModel = fullRecipeMapper.toFullRecipeDataModel(recipe)
		val recipeId = fullRecipeDataModel.recipe.href

		deleteDataByRecipeId(recipeId)
		insertOrReplaceFullRecipe(fullRecipeDataModel)
	}

	override suspend fun saveRecipe(recipeId: String) {
		val recipeToSave = setRecipeIdAndIsTemp(recipeDao.findFullRecipeById(TEMP_RECIPE_ID), recipeId, false)

		deleteDataByRecipeId(TEMP_RECIPE_ID)
		recipeDao.deleteByRecipeId(TEMP_RECIPE_ID)

		recipeToSave?.let { recipe ->
			insertOrReplaceFullRecipe(recipe)
			summarizedRecipeDao.insertAll(
				SummarizedRecipeDataModel(
					href = recipe.recipe.href,
					imgSrc = recipe.recipe.imgSrc,
					title = recipe.recipe.title,
				)
			)
		}
	}

	override suspend fun setRecipeBackToTemp(recipeId: String) {
		val recipe = setRecipeIdAndIsTemp(recipeDao.findFullRecipeById(recipeId), TEMP_RECIPE_ID, true)
		recipe?.let {
			insertOrReplaceFullRecipe(recipe)
			summarizedRecipeDao.deleteByRecipeId(recipeId)
		}
	}

	override suspend fun deleteRecipe(recipeId: String) {
		deleteDataByRecipeId(recipeId)
		recipeDao.deleteByRecipeId(recipeId)
		summarizedRecipeDao.deleteByRecipeId(recipeId)
	}

	override suspend fun getAllAuthorsName(): List<String> =
		recipeDao.getAllSubtitles()
			.map { subtitle -> subtitleMapper.toAuthorDomainModel(subtitle) }
			.distinct()

	private suspend fun insertOrReplaceFullRecipe(fullRecipeDataModel: FullRecipeDataModel) {
		recipeDao.insertOrReplace(fullRecipeDataModel.recipe)
		tagDao.insertOrReplace(*fullRecipeDataModel.tags.toTypedArray())
		ingredientDao.insertOrReplace(*fullRecipeDataModel.ingredients.toTypedArray())
		instructionDao.insertOrReplace(*fullRecipeDataModel.instructions.toTypedArray())
	}

	private suspend fun deleteDataByRecipeId(recipeId: String) {
		tagDao.deleteAllByRecipeId(recipeId)
		ingredientDao.deleteAllByRecipeId(recipeId)
		instructionDao.deleteAllByRecipeId(recipeId)
	}

	private fun setRecipeIdAndIsTemp(fullRecipe: FullRecipeDataModel?, recipeId: String, isTemporary: Boolean) =
		fullRecipe?.copy(
			recipe = fullRecipe.recipe.copy(
				href = recipeId,
				isTemporary = isTemporary,
			),
			tags = fullRecipe.tags.map { it.copy(recipeId = recipeId) },
			ingredients = fullRecipe.ingredients.map { it.copy(recipeId = recipeId) },
			instructions = fullRecipe.instructions.map { it.copy(recipeId = recipeId) },
		)
}

private const val TEMP_RECIPE_ID = ""
