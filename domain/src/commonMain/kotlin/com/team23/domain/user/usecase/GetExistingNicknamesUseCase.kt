package com.team23.domain.user.usecase

class GetExistingNicknamesUseCase(
    // private val recipeRepository: RecipeRepository,
) {
    suspend fun invoke(): List<String> = listOf("1")// recipeRepository.getAllAuthorsName()
}
