package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetAllSummarizedRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): Result<Pair<List<RecipeDomainModel.Summarized>, Int>> = runCatching {
        val currentCount = recipeRepository.getCountSummarizedRecipes()
        recipeRepository.loadAllSummarizedRecipesIfNeeded()
        val recipes = recipeRepository.getAllSummarizedRecipes()
        val newRecipesCount = recipes.size - currentCount
        Pair(recipes, newRecipesCount)
    }
}
