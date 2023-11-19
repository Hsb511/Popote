package com.team23.domain.recipe.usecase

import com.team23.domain.fixtures.getEmptyFullRecipe
import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class LoadTemporaryRecipeUseCaseTest {
	private val recipeRepository: RecipeRepository = mockk()
	private val loadTemporaryRecipeUseCase = LoadTemporaryRecipeUseCase(recipeRepository)

	@Test
	fun `Given full recipe is retrieved, When use case is invoked, Then returns full recipe`() = runTest {
		// Given
		coEvery { recipeRepository.getFullRecipeById("") } returns getEmptyFullRecipe()

		// When
		val result = loadTemporaryRecipeUseCase.invoke()

		// Then
		val expected = getEmptyFullRecipe()
		assertEquals(expected, result)
	}

	@Test
	fun `Given repository throws an error, When use case is invoked, Then returns null`() = runTest {
		// Given
		coEvery { recipeRepository.getFullRecipeById("") } throws RuntimeException()

		// When
		val result = loadTemporaryRecipeUseCase.invoke()

		// Then
		assertNull(result)
	}
}
