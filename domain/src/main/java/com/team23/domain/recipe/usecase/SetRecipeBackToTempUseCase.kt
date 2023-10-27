package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class SetRecipeBackToTempUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipeId: String) {
		recipeRepository.setRecipeBackToTemp(recipeId)
	}
}