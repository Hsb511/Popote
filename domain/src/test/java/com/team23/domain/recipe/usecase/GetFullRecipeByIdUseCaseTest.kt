package com.team23.domain.recipe.usecase

import com.team23.domain.fixtures.getEmptyFullRecipe
import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@ExperimentalCoroutinesApi
class GetFullRecipeByIdUseCaseTest {
    private val recipeRepository: RecipeRepository = mockk {
        coJustRun { loadFullRecipeByIdFromNeuracrIfNeeded(RECIPE_ID) }
    }
    private val getFullRecipeByIdUseCase = GetFullRecipeByIdUseCase(recipeRepository)

    @Test
    fun `Given repository return valid recipe, When use case is invoked, Then return success result of recipe`() = runTest {
        // Given
        coEvery { recipeRepository.getFullRecipeById(RECIPE_ID) } returns getEmptyFullRecipe(RECIPE_ID)

        // When
        val actualResultRecipe = getFullRecipeByIdUseCase.invoke(RECIPE_ID)

        // Then
        val expectedResultRecipe = Result.success(getEmptyFullRecipe(RECIPE_ID))
        assertEquals(expectedResultRecipe, actualResultRecipe)
    }

    @Test
    fun `Given repository null recipe, When use case is invoked, Then return failure result`() = runTest {
        // Given
        coEvery { recipeRepository.getFullRecipeById(RECIPE_ID) } returns null

        // When
        val actualResultRecipe = getFullRecipeByIdUseCase.invoke(RECIPE_ID)

        // Then
        assertTrue(actualResultRecipe.isFailure)
    }
}

private const val RECIPE_ID = "recipeId"
