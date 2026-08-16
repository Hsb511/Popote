package com.team23.data.repository

import com.team23.data.datasource.PopoteLocalDataSource
import com.team23.data.mappers.SourceMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.models.FavoriteDataModel
import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

internal class FavoriteDataRepository(
	popoteLocalDataSource: PopoteLocalDataSource,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
	private val sourceMapper: SourceMapper,
) : FavoriteRepository {
	private val favoriteDao = popoteLocalDataSource.favoriteDao
	private val groceryListDao = popoteLocalDataSource.groceryListDao
	private val summarizedRecipeDao = popoteLocalDataSource.summarizedRecipeDao
	private val baseRecipeDao = popoteLocalDataSource.baseRecipeDao

	override suspend fun updateFavorite(recipeId: String): Boolean {
		if (favoriteDao.isStored(recipeId)) {
			favoriteDao.delete(recipeId)
		} else {
			favoriteDao.insertOrReplace(FavoriteDataModel(recipeId = recipeId))
		}
		return favoriteDao.isStored(recipeId)
	}

	override fun getAllFavorites(): Flow<List<RecipeDomainModel.Summarized>> =
		combine(favoriteDao.getAllFavorites(), groceryListDao.getAll()) { recipeIdList, groceryItems ->
			val summarizedRecipe = summarizedRecipeDao.getAll().filter { summarizedRecipe -> recipeIdList.contains(summarizedRecipe.href) }
			summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipe).map { recipe ->
				recipe.copy(
					isFavorite = true,
					isInGroceryList = recipe.id in groceryItems.map { it.recipeId },
					source = sourceMapper.toDomainSource(
						baseRecipe = baseRecipeDao.findBaseRecipeById(recipe.id)
					),
				)
			}
		}

	override suspend fun clearAllFavorites() {
		favoriteDao.deleteAll()
	}
}
