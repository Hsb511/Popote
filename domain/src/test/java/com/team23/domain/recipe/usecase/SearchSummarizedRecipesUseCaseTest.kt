package com.team23.domain.recipe.usecase

import com.team23.domain.fixtures.getEmptySummarizedRecipe
import com.team23.domain.recipe.repository.RecipeRepository
import com.team23.domain.tag.repository.TagRepository
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchSummarizedRecipesUseCaseTest {
	private val recipeRepository: RecipeRepository = mockk()
	private val tagRepository: TagRepository = mockk()
	private val searchSummarizedRecipesUseCase = SearchSummarizedRecipesUseCase(
		recipeRepository, tagRepository,
	)

	@Test
	fun `Given full search text and non empty tags list, When invoking use case, Then returns flow of summarize recipes`() = runTest {
		// Given
		val searchText = "soup"
		val tagsList = listOf("veggie")
		every {
			recipeRepository.getSummarizedRecipesBySearchText(searchText)
		} returns flowOf(summarizedRecipesFromSearch)
		every {
			tagRepository.getRecipeIdByTags(tagsList)
		} returns flowOf(listOf("1", "3"))

		// When
		val result = searchSummarizedRecipesUseCase.invoke(searchText, tagsList)

		// Then
		val expectedRecipes = listOf(thirdSummarizedRecipe, firstSummarizedRecipe)

		assertEquals(expectedRecipes, result.toList()[0])
	}

	@Test
	fun `Given full search text and empty tags list, When invoking use case, Then returns flow of summarize recipes`() = runTest {
		// Given
		val searchText = "soup"
		val tagsList = emptyList<String>()
		every {
			recipeRepository.getSummarizedRecipesBySearchText(searchText)
		} returns flowOf(summarizedRecipesFromSearch)
		every { tagRepository.getRecipeIdByTags(tagsList) } returns flowOf(emptyList())

		// When
		val result = searchSummarizedRecipesUseCase.invoke(searchText, tagsList)

		// Then
		val expectedRecipes = listOf(secondSummarizedRecipe, thirdSummarizedRecipe, firstSummarizedRecipe)

		assertEquals(expectedRecipes, result.toList()[0])
	}
}

private val firstSummarizedRecipe = getEmptySummarizedRecipe(id = "1", title = "tomato soup")
private val secondSummarizedRecipe = getEmptySummarizedRecipe(id = "2", title = "chicken soup")
private val thirdSummarizedRecipe = getEmptySummarizedRecipe(id = "3", title = "potato soup")

private val summarizedRecipesFromSearch = listOf(
	firstSummarizedRecipe,
	secondSummarizedRecipe,
	thirdSummarizedRecipe,
)
