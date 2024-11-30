package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllSummarizedRecipesUseCase(
    private val recipeRepository: RecipeRepository,
) {
    fun invoke(): Flow<Result<Pair<List<RecipeDomainModel.Summarized>, Int>>> = flow {
        runCatching {
            val cachedRecipes = recipeRepository.getAllCachedSummarizedRecipes().sortByMostRecent()

            emit(Result.success(cachedRecipes to 0))

            val remoteRecipes = recipeRepository.loadAllRemoteSummarizedRecipes().sortByMostRecent()
            val newRecipes = remoteRecipes
                .filter { remote -> !cachedRecipes.map { it.id }.contains(remote.id) }

            emit(Result.success(cachedRecipes to newRecipes.size))
        }.onFailure { failure ->
            emit(Result.failure(failure))
        }
    }

    private fun List<RecipeDomainModel.Summarized>.sortByMostRecent() = this
        .sortedBy { recipe -> recipe.date }
        .reversed()
}
