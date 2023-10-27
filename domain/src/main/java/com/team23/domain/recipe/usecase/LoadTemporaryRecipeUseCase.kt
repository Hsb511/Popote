package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class LoadTemporaryRecipeUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(): RecipeDomainModel.Full? = runCatching {
		recipeRepository.getFullRecipeById("")
	}.getOrNull()
}
