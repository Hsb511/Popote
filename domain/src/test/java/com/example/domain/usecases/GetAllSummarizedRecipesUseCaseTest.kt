package com.example.domain.usecases

import com.example.domain.fixtures.getEmptySummarizedRecipe
import com.example.domain.models.RecipeDomainModel
import com.example.domain.repositories.RecipeRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class GetAllSummarizedRecipesUseCaseTest {
    private val recipeRepository: RecipeRepository = mockk {
        coEvery { loadAllSummarizedRecipesIfNeeded() } returns Unit
    }
    private val getAllSummarizedRecipesUseCase = GetAllSummarizedRecipesUseCase(recipeRepository)

    @Test
    fun `Given repository returns list of recipes, When use case is invoked, Then return same list`() = runTest {
        // Given
        coEvery { recipeRepository.getAllSummarizedRecipes() } returns List(3) {
            getEmptySummarizedRecipe("$it")
        }

        // When
        val actualRecipes = getAllSummarizedRecipesUseCase.invoke()
        val expectedRecipes = Result.success(List(3) {
            getEmptySummarizedRecipe("$it")
        })

        // Then
        coVerify(exactly = 1) {
            recipeRepository.loadAllSummarizedRecipesIfNeeded()
            recipeRepository.getAllSummarizedRecipes()
        }
        assertEquals(expectedRecipes, actualRecipes)
    }

    @Test
    fun `Given repository returns empty list, When use case is invoked, Then return empty list`() = runTest {
        // Given
        coEvery { recipeRepository.getAllSummarizedRecipes() } returns emptyList()

        // When
        val actualRecipes = getAllSummarizedRecipesUseCase.invoke()
        val expectedRecipes = Result.success(emptyList<RecipeDomainModel>())

        // Then
        coVerify(exactly = 1) {
            recipeRepository.loadAllSummarizedRecipesIfNeeded()
            recipeRepository.getAllSummarizedRecipes()
        }
        assertEquals(expectedRecipes, actualRecipes)
    }

    @Test
    fun `Given repository throws exception, When use case is invoked, Then return empty list`() = runTest {
        // Given
        coEvery { recipeRepository.loadAllSummarizedRecipesIfNeeded() } throws IllegalStateException()

        // When
        val actualRecipes = getAllSummarizedRecipesUseCase.invoke()

        // Then
        coVerify(exactly = 1) { recipeRepository.loadAllSummarizedRecipesIfNeeded() }
        coVerify(exactly = 0) { recipeRepository.getAllSummarizedRecipes() }
        assertTrue(actualRecipes.isFailure)
    }
}
