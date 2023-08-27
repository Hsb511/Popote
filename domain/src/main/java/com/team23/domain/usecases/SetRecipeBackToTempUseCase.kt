package com.team23.domain.usecases

import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class SetRecipeBackToTempUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipeId: String) {
		recipeRepository.setRecipeBackToTemp(recipeId)
	}
}