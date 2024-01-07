package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import kotlinx.datetime.LocalDate

class GetAllSummarizedRecipesUseCase(
    // private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): Result<Pair<List<RecipeDomainModel.Summarized>, Int>> = runCatching {
        // val currentCount = recipeRepository.getCountSummarizedRecipes()
        // recipeRepository.loadAllSummarizedRecipesIfNeeded()
        val recipes = List(3) {
            RecipeDomainModel.Summarized(
                id = "hac",
                title = "elementum",
                imageUrl = "https://search.yahoo.com/search?p=dico",
                date = LocalDate(2023, 3, 2),
                language = LanguageDomainModel.ENGLISH,
                isFavorite = false,
                source = RecipeDomainModel.Source.Remote,
            )
        }
        /* recipeRepository.getAllSummarizedRecipes()
            .sortedBy { recipe -> recipe.date }
            .reversed() */
        val newRecipesCount = recipes.size // - currentCount
        Pair(recipes, newRecipesCount)
    }
}
