package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class SaveRecipeUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipe: RecipeDomainModel.Full): String {
		with(recipe.date) {
			val month = "$monthValue".padStart(2, '0')
			val recipeId = "/recipes/$year/$month/$dayOfMonth/${recipe.title.replace(" ", "_").lowercase()}"
			recipeRepository.saveRecipe(recipeId)
			return recipeId
		}
	}
}
