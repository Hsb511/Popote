package com.team23.data.mappers

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.LocalDate
import java.time.format.DateTimeParseException

class DateMapperTest {
    private val dateMapper = DateMapper()

    @Test
    fun `Given valid date string, When toLocalDate is called, Then return valid corresponding LocalDate`() {
        // Given
        val validDateString = "2023/04/23"

        // When
        val actualLocalDate = dateMapper.toLocalDate(validDateString)
        val expectedLocalDate = LocalDate.of(2023, 4, 23)

        // Then
        assertEquals(expectedLocalDate, actualLocalDate)
    }


    @Test(expected = DateTimeParseException::class)
    fun `Given invalid date string, When toLocalDate is called, Then throw IllegalArgumentException`() {
        // Given
        val invalidDateString = "2023/13/23"

        // When
        dateMapper.toLocalDate(invalidDateString)

        // Then
        // Expect DateTimeParseException
    }

    @Test(expected = DateTimeParseException::class)
    fun `Given empty date string, When toLocalDate is called, Then throw IllegalArgumentException`() {
        // Given
        val emptyDateString = ""

        // When
        dateMapper.toLocalDate(emptyDateString)

        // Then
        // Expect DateTimeParseException
    }
}
