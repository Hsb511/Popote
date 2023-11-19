package com.team23.domain.recipe.usecase

import com.team23.domain.fixtures.getEmptyFullRecipe
import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UpdateTempRecipeUseCaseTest {
	private val createNewRecipeUseCase: CreateNewRecipeUseCase = mockk {
		every { this@mockk.invoke() } returns getEmptyFullRecipe()
	}
	private val recipeRepository: RecipeRepository = mockk()
	private val updateTempRecipeUseCase = UpdateTempRecipeUseCase(
		createNewRecipeUseCase, recipeRepository,
	)

	@Test
	fun `Given not new full recipe, When invoking use case, Then update temp recipe`() = runTest {
		// Given
		val recipe = getEmptyFullRecipe(title = "title")
		coJustRun { recipeRepository.updateRecipe(recipe) }

		// When
		updateTempRecipeUseCase.invoke(recipe)

		coVerify(exactly = 1) { recipeRepository.updateRecipe(recipe) }
	}

	@Test
	fun `Given new full recipe, When invoking use case, Then don't update temp recipe`() = runTest {
		// Given
		val recipe = getEmptyFullRecipe()
		coJustRun { recipeRepository.updateRecipe(recipe) }

		// When
		updateTempRecipeUseCase.invoke(recipe)

		coVerify(exactly = 0) { recipeRepository.updateRecipe(recipe) }
	}
}
