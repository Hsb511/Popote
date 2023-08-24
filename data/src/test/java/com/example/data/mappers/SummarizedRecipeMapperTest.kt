package com.team23.data.mappers

import com.team23.data.fixtures.*
import com.team23.data.models.SummarizedRecipeDataModel
import com.team23.domain.models.RecipeDomainModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class SummarizedRecipeMapperTest {
	private val dateMapper: DateMapper = mockk()
	private val languageMapper: LanguageMapper = mockk()
	private val summarizedRecipeMapper = SummarizedRecipeMapper(dateMapper, languageMapper)

	@Test
	fun `Given valid data models, When toSummarizedRecipeDomainModels, Then return valid corresponding domain models`() {
		// Given
		val validDataModels = listOf(
			getFirstSummarizedRecipeFromRawHtml(),
			getSecondSummarizedRecipeFromRawHtml(),
		)
		every { dateMapper.toLocalDateFromHrefDate("2022/12/19") } returns getFirstLocalDate()
		every { dateMapper.toLocalDateFromHrefDate("2022/12/04") } returns getSecondLocalDate()

		// When
		val actualDomainModels = summarizedRecipeMapper.toSummarizedRecipeDomainModels(validDataModels)
		val expectedDomainModels = listOf(
			getFirstSummarizedRecipeDomainModel(),
			getSecondSummarizedRecipeDomainModel()
		)

		// Then
		assertEquals(expectedDomainModels, actualDomainModels)
	}

	@Test
	fun `Given empty data models, When toSummarizedRecipeDomainModels, Then return empty domain models`() {
		// Given
		val emptyDataModels = emptyList<SummarizedRecipeDataModel>()

		// When
		val actualDomainModels = summarizedRecipeMapper.toSummarizedRecipeDomainModels(emptyDataModels)
		val expectedDomainModels = emptyList<RecipeDomainModel.Summarized>()

		// Then
		assertEquals(expectedDomainModels, actualDomainModels)
	}
}
