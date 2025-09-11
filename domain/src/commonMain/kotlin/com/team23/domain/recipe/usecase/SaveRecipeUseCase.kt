package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.LanguageDomainModel.FRENCH
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import kotlinx.datetime.number

class SaveRecipeUseCase(
    private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(recipe: RecipeDomainModel.Full): String {
        with(recipe.date) {
            val day = "$day".padStart(2, '0')
            val month = "${month.number}".padStart(2, '0')
            val cleanedRecipeTitle = recipe.title.replace(" ", "_").lowercase()
            val language = if (recipe.language == FRENCH) "_fr" else "_"
            val recipeId = "/recipes/$year/$month/$day/$cleanedRecipeTitle$language"
            recipeRepository.saveRecipe(recipeId)
            return recipeId
        }
    }
}
