package com.team23.data.repositories

import com.team23.data.daos.SummarizedRecipeDao
import com.team23.data.datasources.NeuracrWebsiteDataSource
import com.team23.data.parsers.SummarizedRecipeParser
import com.team23.data.mappers.SummarizedRecipeMapper
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeDataRepositoryTest {
    private val summarizedRecipeDao: SummarizedRecipeDao = mockk()
    private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource = mockk()
    private val summarizedRecipeParser: SummarizedRecipeParser = mockk()
    private val summarizedRecipeMapper: SummarizedRecipeMapper = mockk()

    private val recipeDataRepository = RecipeDataRepository(
        summarizedRecipeDao, neuracrWebsiteDataSource, summarizedRecipeParser, summarizedRecipeMapper
    )

    @Test
    fun `Given repo return recipe data models, When getAllSummarizedRecipes is called, Then return valid domain models`() = runTest {
        // Given
        //every { recipeDao.getAll() } returns

    }
}