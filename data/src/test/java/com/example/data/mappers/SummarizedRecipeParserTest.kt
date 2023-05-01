package com.team23.data.mappers

import com.team23.data.fixtures.getFirstSummarizedRecipeFromRawHtml
import com.team23.data.fixtures.getSecondSummarizedRecipeFromRawHtml
import com.team23.data.fixtures.rawHtmlToParse
import com.team23.data.models.SummarizedRecipeDataModel
import com.team23.data.parsers.SummarizedRecipeParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements
import org.junit.Assert.assertEquals
import org.junit.Test

class SummarizedRecipeParserTest {
    private val summarizedRecipeParser = SummarizedRecipeParser()

    @Test
    fun `Given valid raw Elements, When toSummarizedRecipeDataModels is called, Then return valid data models`() {
        // Given
        val validRawElements = Jsoup
            .parse(rawHtmlToParse)
            .select("ul.latest-recipes")
            .select("li")

        // When
        val actualDataModels = summarizedRecipeParser.toSummarizedRecipeDataModels(validRawElements)
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
        summarizedRecipeParser.toSummarizedRecipeDataModels(invalidRawElements)

        // Then
        // Expect IllegalArgumentException
    }

    @Test
    fun `Given empty raw Elements, When toSummarizedRecipeDataModels is called, Then empty list`() {
        // Given
        val emptyRawElements = Elements()

        // When
        val actualDataModels = summarizedRecipeParser.toSummarizedRecipeDataModels(emptyRawElements)
        val expectedDataModels = emptyList<SummarizedRecipeDataModel>()

        // Then
        assertEquals(expectedDataModels, actualDataModels)
    }
}
