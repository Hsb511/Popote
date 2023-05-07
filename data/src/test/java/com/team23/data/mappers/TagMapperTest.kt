package com.team23.data.mappers

import org.junit.Assert.assertEquals
import org.junit.Test


internal class TagMapperTest {
	private val tagMapper = TagMapper()

	@Test
	fun `Given alsacien tag, When translateBackToEnglish is called, Then returns alsacian`() {
		// Given
		val givenTag = "alsacien"

		// When
		val actualEnglishTag = tagMapper.translateBackToEnglish(givenTag)

		// Then
		val expectedEnglishTag = listOf("alsacian")
		assertEquals(expectedEnglishTag, actualEnglishTag)
	}

	@Test
	fun `Given vegetarien tag, When translateBackToEnglish is called, Then returns vegetarian et veggie`() {
		// Given
		val givenTag = "végétarien"

		// When
		val actualEnglishTag = tagMapper.translateBackToEnglish(givenTag)

		// Then
		val expectedEnglishTag = listOf("vegetarian", "veggie")
		assertEquals(expectedEnglishTag, actualEnglishTag)
	}

	@Test
	fun `Given chinese tag, When translateBackToEnglish is called, Then returns chinese`() {
		// Given
		val givenTag = "chinese"

		// When
		val actualEnglishTag = tagMapper.translateBackToEnglish(givenTag)

		// Then
		val expectedEnglishTag = listOf("chinese")
		assertEquals(expectedEnglishTag, actualEnglishTag)
	}

	@Test
	fun `Given empty tag, When translateBackToEnglish is called, Then returns empty list`() {
		// Given
		val givenTag = ""

		// When
		val actualEnglishTag = tagMapper.translateBackToEnglish(givenTag)

		// Then
		val expectedEnglishTag = emptyList<String>()
		assertEquals(expectedEnglishTag, actualEnglishTag)
	}
}
