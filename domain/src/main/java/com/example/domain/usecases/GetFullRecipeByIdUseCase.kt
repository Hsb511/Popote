package com.example.domain.usecases

import com.example.domain.models.RecipeDomainModel
import com.example.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetFullRecipeByIdUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend fun invoke(id: String): Result<RecipeDomainModel.Full> = runCatching {
        recipeRepository.getFullRecipeById(id) ?: throw IllegalArgumentException()
    }
}
