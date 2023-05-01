package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetFullRecipeByIdUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend fun invoke(recipeId: String): Result<RecipeDomainModel.Full> = runCatching {
        recipeRepository.loadFullRecipeByIdFromNeuracrIfNeeded(recipeId)
        recipeRepository.getFullRecipeById(recipeId) ?: throw IllegalArgumentException()
    }
}
