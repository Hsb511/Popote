package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class LoadTemporaryRecipeUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(): RecipeDomainModel.Full? = runCatching {
		recipeRepository.getFullRecipeById("")
	}.getOrNull()
}
