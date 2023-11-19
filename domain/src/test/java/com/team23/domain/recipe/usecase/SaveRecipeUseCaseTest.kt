package com.team23.domain.recipe.usecase

import com.team23.domain.fixtures.getEmptyFullRecipe
import com.team23.domain.recipe.model.LanguageDomainModel
import com.team23.domain.recipe.repository.RecipeRepository
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate


@OptIn(ExperimentalCoroutinesApi::class)
class SaveRecipeUseCaseTest {
	private val recipeRepository: RecipeRepository = mockk()
	private val saveRecipeUseCase = SaveRecipeUseCase(recipeRepository)

	@Test
	fun `Given full recipe, When invoking use case, Then save recipe by id and return that id`() = runTest {
		// Given
		val recipe = getEmptyFullRecipe(
			title = "CLEAN RECIPE TITLE",
			date = LocalDate.of(2023, 2, 3),
			language = LanguageDomainModel.FRENCH,
		)
		val expectedRecipeId = "/recipes/2023/02/03/clean_recipe_title_fr"
		coJustRun { recipeRepository.saveRecipe(expectedRecipeId) }

		// When
		val result = saveRecipeUseCase.invoke(recipe)

		// Then
		coVerify(exactly = 1) { recipeRepository.saveRecipe(expectedRecipeId)  }
		assertEquals(expectedRecipeId, result)
	}
}
