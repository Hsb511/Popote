package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class GetFullRecipeByIdUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository
) {
    suspend fun invoke(recipeId: String): Result<RecipeDomainModel.Full> = runCatching {
        recipeRepository.loadFullRecipeByIdFromNeuracrIfNeeded(recipeId)
        recipeRepository.getFullRecipeById(recipeId) ?: throw IllegalArgumentException()
    }
}
