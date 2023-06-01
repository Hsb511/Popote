package com.team23.data.repositories

import com.team23.data.daos.FavoriteDao
import com.team23.data.daos.SummarizedRecipeDao
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.models.FavoriteDataModel
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
	private val favoriteDao: FavoriteDao,
	private val summarizedRecipeDao: SummarizedRecipeDao,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
) : FavoriteRepository {
	override suspend fun updateFavorite(recipeId: String) {
		if (favoriteDao.isStored(recipeId)) {
			favoriteDao.delete(recipeId)
		} else {
			favoriteDao.insertOrReplace(FavoriteDataModel(recipeId = recipeId))
		}
	}

	override fun getAllFavorites(): Flow<List<RecipeDomainModel.Summarized>> =
		favoriteDao.getAllFavorites()
			.map { recipeIdList -> summarizedRecipeDao.getAll().filter { summarizedRecipe -> recipeIdList.contains(summarizedRecipe.href) } }
			.map { summarizedRecipe -> summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipe) }
}
