package com.team23.domain.repositories

import com.team23.domain.models.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
	suspend fun updateFavorite(recipeId: String)
	fun getAllFavorites(): Flow<List<RecipeDomainModel.Summarized>>
}
