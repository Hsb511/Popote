package com.team23.domain.favorite.usecase

import com.team23.domain.favorite.repository.FavoriteRepository
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
	private val favoriteRepository: FavoriteRepository,
) {

	suspend fun invoke(recipeId: String) {
		favoriteRepository.updateFavorite(recipeId = recipeId)
	}
}
