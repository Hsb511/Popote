package com.example.domain.usecases

import com.example.domain.models.RecipeDomainModel
import com.example.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetAllSummarizedRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): Result<List<RecipeDomainModel.Summarized>> = runCatching {
        recipeRepository.loadAllSummarizedRecipesIfNeeded()
        recipeRepository.getAllSummarizedRecipes()
    }
}
