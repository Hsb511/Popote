package com.team23.domain.favorite.usecase

import com.team23.domain.favorite.repository.FavoriteRepository
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.LocalDate

class GetAllFavoritesUseCase(
    //  private val favoriteRepository: FavoriteRepository,
) {

    fun invoke(): Flow<List<RecipeDomainModel.Summarized>> = flowOf(List(3) {

        RecipeDomainModel.Summarized(
            id = "utamur",
            title = "diam",
            imageUrl = "https://www.google.com/#q=postea",
            date = LocalDate(2023, 3, 2),
            language = LanguageDomainModel.ENGLISH,
            isFavorite = false,
            source = RecipeDomainModel.Source.Remote,
        )
    })// favoriteRepository.getAllFavorites()
}
