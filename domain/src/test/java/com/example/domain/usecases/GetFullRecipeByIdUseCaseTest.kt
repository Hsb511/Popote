package com.team23.domain.usecases

import com.team23.domain.fixtures.getEmptyFullRecipe
import com.team23.domain.fixtures.getEmptySummarizedRecipe
import com.team23.domain.repositories.RecipeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class GetFullRecipeByIdUseCaseTest {
    private val recipeRepository: RecipeRepository = mockk()
    private val getFullRecipeByIdUseCase = GetFullRecipeByIdUseCase(recipeRepository)

    @Test
    fun `Given repository return valid recipe, When use case is invoked, Then return success result of recipe`() = runTest {
        // Given
        val recipeId = "recipeId"
        coEvery { recipeRepository.getFullRecipeById(recipeId) } returns getEmptyFullRecipe(recipeId)

        // When
        val actualResultRecipe = getFullRecipeByIdUseCase.invoke(recipeId)
        val expectedResultRecipe = Result.success(getEmptyFullRecipe(recipeId))

        // Then
        assertEquals(expectedResultRecipe, actualResultRecipe)
    }

    @Test
    fun `Given repository null recipe, When use case is invoked, Then return failure result`() = runTest {
        // Given
        val recipeId = "recipeId"
        coEvery { recipeRepository.getFullRecipeById(recipeId) } returns null

        // When
        val actualResultRecipe = getFullRecipeByIdUseCase.invoke(recipeId)

        // Then
        assertTrue(actualResultRecipe.isFailure)
    }
}
