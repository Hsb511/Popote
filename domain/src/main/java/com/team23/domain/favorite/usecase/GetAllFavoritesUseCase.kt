package com.team23.domain.favorite.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.favorite.repository.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFavoritesUseCase @Inject constructor(
	private val favoriteRepository: FavoriteRepository,
) {

	fun invoke(): Flow<List<RecipeDomainModel.Summarized>> = favoriteRepository.getAllFavorites()
}
