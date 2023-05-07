package com.example.data.repositories

import com.team23.data.daos.*
import com.team23.data.datasources.NeuracrWebsiteDataSource
import com.team23.data.mappers.FullRecipeMapper
import com.team23.data.mappers.SummarizedRecipeMapper
import com.team23.data.parsers.FullRecipeParser
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.data.repositories.RecipeRepositoryImpl
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeRepositoryImplTest {
	private val summarizedRecipeDao: SummarizedRecipeDao = mockk()
	private val recipeDao: RecipeDao = mockk()
	private val tagDao: TagDao = mockk()
	private val ingredientDao: IngredientDao = mockk()
	private val instructionDao: InstructionDao = mockk()
	private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource = mockk()
	private val summarizedRecipeParser: SummarizedRecipeParser = mockk()
	private val summarizedRecipeMapper: SummarizedRecipeMapper = mockk()
	private val fullRecipeParser: FullRecipeParser = mockk()
	private val fullRecipeMapper: FullRecipeMapper = mockk()

	private val recipeRepositoryImpl = RecipeRepositoryImpl(
		summarizedRecipeDao,
		recipeDao,
		tagDao,
		ingredientDao,
		instructionDao,
		neuracrWebsiteDataSource,
		summarizedRecipeParser,
		fullRecipeParser,
		summarizedRecipeMapper,
		fullRecipeMapper,
	)

	@Test
	fun `Given repo return recipe data models, When getAllSummarizedRecipes is called, Then return valid domain models`() =
		runTest {
			// Given
			//every { recipeDao.getAll() } returns
			recipeRepositoryImpl
		}
}