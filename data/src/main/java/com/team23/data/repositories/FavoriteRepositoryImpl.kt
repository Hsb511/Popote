package com.team23.data.repositories

import com.team23.data.daos.FavoriteDao
import com.team23.data.daos.RecipeDao
import com.team23.data.daos.SummarizedRecipeDao
import com.team23.data.mappers.SourceMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.models.FavoriteDataModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(
	private val favoriteDao: FavoriteDao,
	private val recipeDao: RecipeDao,
	private val summarizedRecipeDao: SummarizedRecipeDao,
	private val summarizedRecipeMapper: SummarizedRecipeMapper,
	private val sourceMapper: SourceMapper,
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
			.map { recipeIdList ->
				summarizedRecipeDao.getAll().filter { summarizedRecipe -> recipeIdList.contains(summarizedRecipe.href) }
			}
			.map { summarizedRecipe ->
				summarizedRecipeMapper.toSummarizedRecipeDomainModels(summarizedRecipe).map { recipe ->
					val baseRecipe = recipeDao.findFullRecipeById(recipe.id)?.recipe
					recipe.copy(
						isFavorite = favoriteDao.isStored(recipe.id),
						source = sourceMapper.toDomainSource(baseRecipe),
					)
				}
			}
}
