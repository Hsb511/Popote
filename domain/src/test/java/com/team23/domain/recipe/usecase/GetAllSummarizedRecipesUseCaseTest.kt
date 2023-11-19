package com.team23.domain.recipe.usecase

import com.team23.domain.fixtures.getEmptySummarizedRecipe
import com.team23.domain.recipe.model.RecipeDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coJustRun
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
        coEvery { getCountSummarizedRecipes() } returns RECIPE_COUNT
        coJustRun { loadAllSummarizedRecipesIfNeeded() }
    }
    private val getAllSummarizedRecipesUseCase = GetAllSummarizedRecipesUseCase(recipeRepository)

    @Test
    fun `Given repository returns count and list of recipes, When use case is invoked, Then return count and same list`() = runTest {
        // Given
        coEvery { recipeRepository.getAllSummarizedRecipes() } returns List(3) {
            getEmptySummarizedRecipe("$it")
        }

        // When
        val actualResult = getAllSummarizedRecipesUseCase.invoke()

        // Then
        val expectedRecipes = listOf(
            getEmptySummarizedRecipe("2"),
            getEmptySummarizedRecipe("1"),
            getEmptySummarizedRecipe("0"),
        )
        val expectedResult =  Result.success(Pair(expectedRecipes, -20))
        coVerify(exactly = 1) {
            recipeRepository.getCountSummarizedRecipes()
            recipeRepository.loadAllSummarizedRecipesIfNeeded()
            recipeRepository.getAllSummarizedRecipes()
        }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given repository returns empty list, When use case is invoked, Then return empty list`() = runTest {
        // Given
        coEvery { recipeRepository.getAllSummarizedRecipes() } returns emptyList()

        // When
        val actualResult = getAllSummarizedRecipesUseCase.invoke()
        val expectedRecipes = emptyList<RecipeDomainModel>()
        val expectedResult = Result.success(Pair(expectedRecipes, -23))

        // Then
        coVerify(exactly = 1) {
            recipeRepository.loadAllSummarizedRecipesIfNeeded()
            recipeRepository.getAllSummarizedRecipes()
        }
        assertEquals(expectedResult, actualResult)
    }

    @Test
    fun `Given repository throws exception on loading recipes, When use case is invoked, Then return empty list`() = runTest {
        // Given
        coEvery { recipeRepository.loadAllSummarizedRecipesIfNeeded() } throws IllegalStateException()

        // When
        val actualRecipes = getAllSummarizedRecipesUseCase.invoke()

        // Then
        coVerify(exactly = 1) { recipeRepository.loadAllSummarizedRecipesIfNeeded() }
        coVerify(exactly = 0) { recipeRepository.getAllSummarizedRecipes() }
        assertTrue(actualRecipes.isFailure)
    }

    @Test
    fun `Given repository throws exception on counting recipes, When use case is invoked, Then return empty list`() = runTest {
        // Given
        coEvery { recipeRepository.getCountSummarizedRecipes() } throws IllegalStateException()

        // When
        val actualRecipes = getAllSummarizedRecipesUseCase.invoke()

        // Then
        coVerify(exactly = 0) {
            recipeRepository.loadAllSummarizedRecipesIfNeeded()
            recipeRepository.getAllSummarizedRecipes()
        }
        assertTrue(actualRecipes.isFailure)
    }
}

private const val RECIPE_COUNT = 23
