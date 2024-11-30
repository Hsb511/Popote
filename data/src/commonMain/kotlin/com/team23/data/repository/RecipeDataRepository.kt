package com.team23.data.repository

import com.fleeksoft.ksoup.select.Elements
import com.team23.data.datasource.NeuracrLocalDataSource
import com.team23.data.datasource.NeuracrWebsiteDataSource
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
import com.team23.domain.recipe.usecase.TEMP_RECIPE_ID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RecipeDataRepository(
    neuracrLocalDataSource: NeuracrLocalDataSource,
    private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource,
    private val summarizedRecipeParser: SummarizedRecipeParser,
    private val summarizedRecipeMapper: SummarizedRecipeMapper,
    private val fullRecipeMapper: FullRecipeMapper,
    private val fullRecipeParser: FullRecipeParser,
    private val sourceMapper: SourceMapper,
    private val subtitleMapper: SubtitleMapper,
) : RecipeRepository {
    private val baseRecipeDao = neuracrLocalDataSource.baseRecipeDao
    private val favoriteDao = neuracrLocalDataSource.favoriteDao
    private val ingredientDao = neuracrLocalDataSource.ingredientDao
    private val instructionDao = neuracrLocalDataSource.instructionDao
    private val summarizedRecipeDao = neuracrLocalDataSource.summarizedRecipeDao
    private val tagDao = neuracrLocalDataSource.tagDao

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
        if (baseRecipeDao.findBaseRecipeById(recipeId) == null) {
            val rawRecipe: Elements = neuracrWebsiteDataSource.getRecipeById(recipeId)
            val fullRecipeDataModel = fullRecipeParser.toFullRecipeDataModel(recipeId, rawRecipe)
            insertOrReplaceFullRecipe(fullRecipeDataModel)
        }
    }

    override suspend fun getFullRecipeById(recipeId: String): RecipeDomainModel.Full? =
        getFullDataRecipeById(recipeId)?.let { fullRecipe ->
            fullRecipeMapper.toFullRecipeDomainModel(fullRecipe).copy(
                isFavorite = favoriteDao.isStored(recipeId)
            )
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
        val recipeToSave =
            setRecipeIdAndIsTemp(getFullDataRecipeById(TEMP_RECIPE_ID), recipeId, false)

        deleteDataByRecipeId(TEMP_RECIPE_ID)
        baseRecipeDao.deleteByRecipeId(TEMP_RECIPE_ID)

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
        val recipe = setRecipeIdAndIsTemp(getFullDataRecipeById(recipeId), TEMP_RECIPE_ID, true)
        recipe?.let {
            insertOrReplaceFullRecipe(recipe)
            summarizedRecipeDao.deleteByRecipeId(recipeId)
        }
    }

    override suspend fun deleteRecipe(recipeId: String) {
        deleteDataByRecipeId(recipeId)
        baseRecipeDao.deleteByRecipeId(recipeId)
        summarizedRecipeDao.deleteByRecipeId(recipeId)
    }

    override suspend fun getAllAuthorsName(): List<String> = baseRecipeDao.getAllSubtitles()
        .map { subtitle -> subtitleMapper.toAuthorDomainModel(subtitle) }
        .distinct()

    private fun insertOrReplaceFullRecipe(fullRecipeDataModel: FullRecipeDataModel) {
        baseRecipeDao.insertOrReplace(fullRecipeDataModel.recipe)
        tagDao.insertOrReplace(fullRecipeDataModel.tags)
        ingredientDao.insertOrReplace(fullRecipeDataModel.ingredients)
        instructionDao.insertOrReplace(fullRecipeDataModel.instructions)
    }

    private fun deleteDataByRecipeId(recipeId: String) {
        tagDao.deleteAllByRecipeId(recipeId)
        ingredientDao.deleteAllByRecipeId(recipeId)
        instructionDao.deleteAllByRecipeId(recipeId)
    }

    private fun getFullDataRecipeById(recipeId: String): FullRecipeDataModel? =
        baseRecipeDao.findBaseRecipeById(recipeId)?.let { baseRecipe ->
            FullRecipeDataModel(
                recipe = baseRecipe,
                tags = tagDao.getTagsByRecipeId(recipeId),
                ingredients = ingredientDao.getAllByRecipeId(recipeId),
                instructions = instructionDao.getAllByRecipeId(recipeId),
            )
        }

    private fun enrichWithFavorite(recipe: RecipeDomainModel.Summarized) =
        recipe.copy(
            isFavorite = favoriteDao.isStored(recipe.id),
            source = sourceMapper.toDomainSource(
                baseRecipe = baseRecipeDao.findBaseRecipeById(
                    recipe.id
                )
            ),
        )

    private fun setRecipeIdAndIsTemp(
        fullRecipe: FullRecipeDataModel?,
        recipeId: String,
        isTemporary: Boolean
    ) =
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
