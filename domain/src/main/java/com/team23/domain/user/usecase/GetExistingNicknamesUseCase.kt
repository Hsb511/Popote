package com.team23.domain.user.usecase

import com.team23.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class GetExistingNicknamesUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
){
	suspend fun invoke(): List<String> = recipeRepository.getAllAuthorsName()
}
