package com.team23.domain.usecases

import com.team23.domain.repositories.FavoriteRepository
import javax.inject.Inject

class UpdateFavoriteUseCase @Inject constructor(
	private val favoriteRepository: FavoriteRepository,
) {

	suspend fun invoke(recipeId: String) {
		favoriteRepository.updateFavorite(recipeId = recipeId)
	}
}
