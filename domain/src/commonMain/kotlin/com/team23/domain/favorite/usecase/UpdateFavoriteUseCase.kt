package com.team23.domain.favorite.usecase

import com.team23.domain.favorite.repository.FavoriteRepository

class UpdateFavoriteUseCase(
   private val favoriteRepository: FavoriteRepository,
) {

    suspend fun invoke(recipeId: String) {
        favoriteRepository.updateFavorite(recipeId = recipeId)
    }
}
