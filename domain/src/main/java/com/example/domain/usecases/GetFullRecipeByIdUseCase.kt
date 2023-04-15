package com.example.domain.usecases

import com.example.domain.models.RecipeDomainModel
import com.example.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetFullRecipeByIdUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend fun invoke(recipeId: String): Result<RecipeDomainModel.Full> = runCatching {
        recipeRepository.loadFullRecipeByIdFromNeuracrIfNeeded(recipeId)
        recipeRepository.getFullRecipeById(recipeId) ?: throw IllegalArgumentException()
    }
}
