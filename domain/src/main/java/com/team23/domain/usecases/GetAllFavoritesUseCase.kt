package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
	private val favoriteRepository: FavoriteRepository,
) {

	fun invoke(): Flow<List<RecipeDomainModel.Summarized>> = favoriteRepository.getAllFavorites()
}
