package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.lang.RuntimeException


@OptIn(ExperimentalCoroutinesApi::class)
class DeleteRecipeUseCaseTest {
	private val recipeRepository: RecipeRepository = mockk()
	private val deleteRecipeUseCase = DeleteRecipeUseCase(recipeRepository)

	@Test
	fun `Given delete recipe runs without error, When use case is invoked, Then returns true`() = runTest {
		// Given
		coJustRun { recipeRepository.deleteRecipe(RECIPE_ID) }

		// When
		val result = deleteRecipeUseCase.invoke(RECIPE_ID)

		// Then
		assertTrue(result)
	}

	@Test
	fun `Given delete recipe runs with error, When use case is invoked, Then returns false`() = runTest {
		// Given
		coEvery { recipeRepository.deleteRecipe(RECIPE_ID) } throws RuntimeException()

		// When
		val result = deleteRecipeUseCase.invoke(RECIPE_ID)

		// Then
		assertFalse(result)
	}
}

private const val RECIPE_ID = "RECIPE_ID"