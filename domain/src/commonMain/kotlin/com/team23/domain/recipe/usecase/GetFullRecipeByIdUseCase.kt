package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import kotlinx.datetime.LocalDate

class GetFullRecipeByIdUseCase(
    // private val recipeRepository: RecipeRepository
) {
    suspend fun invoke(recipeId: String): Result<RecipeDomainModel.Full> = Result.success(
        RecipeDomainModel.Full(
            id = recipeId,
            title = "aptent",
            imageUrl = "https://neuracr.github.io/assets/images/chicken_pineapple_curry.jpg",
            date = LocalDate.fromEpochDays(23),
            language = LanguageDomainModel.ENGLISH,
            isFavorite = false,
            source = RecipeDomainModel.Source.Remote,
            author = "pri",
            tags = listOf("aaa", "nn", "23"),
            servingsNumber = 7272,
            ingredients = listOf(),
            startingText = "adipisci",
            instructions = listOf(),
            endingText = "idque",
            sections = listOf()
        )
    ) /*runCatching {
        recipeRepository.loadFullRecipeByIdFromNeuracrIfNeeded(recipeId)
        recipeRepository.getFullRecipeById(recipeId) ?: throw IllegalArgumentException()
    }*/
}
