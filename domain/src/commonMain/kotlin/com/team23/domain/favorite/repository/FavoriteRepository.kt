package com.team23.domain.favorite.repository

import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
	suspend fun updateFavorite(recipeId: String): Boolean
	fun getAllFavorites(): Flow<List<RecipeDomainModel.Summarized>>
}
