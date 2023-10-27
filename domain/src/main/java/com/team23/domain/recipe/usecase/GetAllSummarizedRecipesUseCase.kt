package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import javax.inject.Inject

class GetAllSummarizedRecipesUseCase @Inject constructor(
	private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): Result<Pair<List<RecipeDomainModel.Summarized>, Int>> = runCatching {
        val currentCount = recipeRepository.getCountSummarizedRecipes()
        recipeRepository.loadAllSummarizedRecipesIfNeeded()
        val recipes = recipeRepository.getAllSummarizedRecipes()
            .sortedBy { recipe -> recipe.date }
            .reversed()
        val newRecipesCount = recipes.size - currentCount
        Pair(recipes, newRecipesCount)
    }
}
