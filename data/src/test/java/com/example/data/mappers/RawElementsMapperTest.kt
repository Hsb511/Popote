package com.example.data.mappers

import com.example.data.fixtures.getFirstSummarizedRecipeFromRawHtml
import com.example.data.fixtures.getSecondSummarizedRecipeFromRawHtml
import com.example.data.fixtures.rawHtmlToParse
import com.example.data.models.SummarizedRecipeDataModel
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.Assert.assertEquals
import org.junit.Test

class RawElementsMapperTest {
    private val rawElementsMapper = RawElementsMapper()

    @Test
    fun `Given valid raw Elements, When toSummarizedRecipeDataModels is called, Then return valid data models`() {
        // Given
        val validRawElements = Jsoup
            .parse(rawHtmlToParse)
            .select("ul.latest-recipes")
            .select("li")

        // When
        val actualDataModels = rawElementsMapper.toSummarizedRecipeDataModels(validRawElements)
        val expectedDataModels = listOf(
            getFirstSummarizedRecipeFromRawHtml(),
            getSecondSummarizedRecipeFromRawHtml(),
        )

        // Then
        assertEquals(expectedDataModels, actualDataModels)
    }

    @Test(expected = IllegalArgumentException::class)
    fun `Given invalid raw Elements, When toSummarizedRecipeDataModels is called, Then throw IllegalArgumentException`() {
        // Given
        val invalidRawElements = Elements(Element("<html></html>"))

        // When
        rawElementsMapper.toSummarizedRecipeDataModels(invalidRawElements)

        // Then
        // Expect IllegalArgumentException
    }

    @Test
    fun `Given empty raw Elements, When toSummarizedRecipeDataModels is called, Then empty list`() {
        // Given
        val emptyRawElements = Elements()

        // When
        val actualDataModels = rawElementsMapper.toSummarizedRecipeDataModels(emptyRawElements)
        val expectedDataModels = emptyList<SummarizedRecipeDataModel>()

        // Then
        assertEquals(expectedDataModels, actualDataModels)
    }
}
