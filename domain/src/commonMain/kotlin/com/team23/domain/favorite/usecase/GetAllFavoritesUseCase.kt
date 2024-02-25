package com.team23.domain.favorite.usecase

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow

class GetAllFavoritesUseCase(
    private val favoriteRepository: FavoriteRepository,
) {

    fun invoke(): Flow<List<RecipeDomainModel.Summarized>> = favoriteRepository.getAllFavorites()
}
