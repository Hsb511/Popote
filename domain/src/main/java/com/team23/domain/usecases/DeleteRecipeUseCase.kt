package com.team23.domain.usecases

import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipeId: String): Boolean = runCatching {
		recipeRepository.deleteRecipe(recipeId)
	}.isSuccess
}
