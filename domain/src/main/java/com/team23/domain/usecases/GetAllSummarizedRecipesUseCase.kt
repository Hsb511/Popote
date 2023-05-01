package com.team23.domain.usecases

import com.team23.domain.models.RecipeDomainModel
import com.team23.domain.repositories.RecipeRepository
import javax.inject.Inject

class GetAllSummarizedRecipesUseCase @Inject constructor(
    private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): Result<List<RecipeDomainModel.Summarized>> = runCatching {
        recipeRepository.loadAllSummarizedRecipesIfNeeded()
        recipeRepository.getAllSummarizedRecipes()
    }
}
