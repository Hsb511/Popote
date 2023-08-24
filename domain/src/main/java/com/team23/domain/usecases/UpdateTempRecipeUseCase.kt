package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class UpdateTempRecipeUseCase @Inject constructor(
	private val createNewRecipeUseCase: CreateNewRecipeUseCase,
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipe: RecipeDomainModel.Full) {
		if (recipe != createNewRecipeUseCase.invoke()) {
			recipeRepository.updateRecipe(recipe)
		}
	}
}