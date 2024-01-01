package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository

class LoadTemporaryRecipeUseCase(
    private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): RecipeDomainModel.Full? = runCatching {
        recipeRepository.getFullRecipeById("")
    }.getOrNull()
}
