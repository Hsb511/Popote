package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.repository.RecipeRepository

class SetRecipeBackToTempUseCase(
    // private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(recipeId: String) {
       // recipeRepository.setRecipeBackToTemp(recipeId)
    }
}