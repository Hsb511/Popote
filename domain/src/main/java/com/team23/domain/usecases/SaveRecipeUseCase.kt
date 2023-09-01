package com.team23.domain.usecases

import com.team23.domain.models.LanguageDomainModel.FRENCH
import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class SaveRecipeUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
	suspend fun invoke(recipe: RecipeDomainModel.Full): String {
		with(recipe.date) {
			val day = "$dayOfMonth".padStart(2, '0')
			val month = "$monthValue".padStart(2, '0')
			val cleanedRecipeTitle = recipe.title.replace(" ", "_").lowercase()
			val language = if (recipe.language == FRENCH) "_fr" else "_"
			val recipeId = "/recipes/$year/$month/$day/$cleanedRecipeTitle$language"
			recipeRepository.saveRecipe(recipeId)
			return recipeId
		}
	}
}
