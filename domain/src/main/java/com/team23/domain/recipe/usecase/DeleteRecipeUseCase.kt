package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class DeleteRecipeUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipeId: String): Boolean = runCatching {
		recipeRepository.deleteRecipe(recipeId)
	}.isSuccess
}
