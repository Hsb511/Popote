package com.team23.domain.recipe.usecase

import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SetRecipeBackToTempUseCaseTest {
	private val recipeRepository: RecipeRepository = mockk {
		coJustRun { setRecipeBackToTemp(RECIPE_ID) }
	}
	private val setRecipeBackToTempUseCase = SetRecipeBackToTempUseCase(recipeRepository)

	@Test
	fun `When invoking use case, Then calls repository methods`() = runTest {
		// When
		setRecipeBackToTempUseCase.invoke(RECIPE_ID)

		// Then
		coVerify(exactly = 1) { recipeRepository.setRecipeBackToTemp(RECIPE_ID) }
	}
}

private const val RECIPE_ID = "RECIPE_ID"
