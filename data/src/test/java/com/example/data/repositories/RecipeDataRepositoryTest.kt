package com.example.data.repositories

import com.example.data.daos.RecipeDao
import com.example.data.datasources.NeuracrWebsiteDataSource
import com.example.data.mappers.RawElementsMapper
import com.example.data.mappers.SummarizedRecipeMapper
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeDataRepositoryTest {
    private val recipeDao: RecipeDao = mockk()
    private val neuracrWebsiteDataSource: NeuracrWebsiteDataSource = mockk()
    private val rawElementsMapper: RawElementsMapper = mockk()
    private val summarizedRecipeMapper: SummarizedRecipeMapper = mockk()

    private val recipeDataRepository = RecipeDataRepository(
        recipeDao, neuracrWebsiteDataSource, rawElementsMapper, summarizedRecipeMapper
    )

    @Test
    fun `Given repo return recipe data models, When getAllSummarizedRecipes is called, Then return valid domain models`() = runTest {
        // Given
        //every { recipeDao.getAll() } returns

    }
}