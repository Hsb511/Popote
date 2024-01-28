package com.team23.data.mappers

import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

class DateMapper {

    fun toLocalDateFromHrefDate(dateStr: String): LocalDate {
        val (year, month, day) = dateStr
            .split("/")
            .map { it.toInt() }
        return LocalDate(year = year, monthNumber = month, dayOfMonth = day)
    }

    fun toLocalDateFromSubtitleDate(dateStr: String): LocalDate {
        val (month, day, year) = dateStr
            .replace(",", "")
            .split(" ")
        return LocalDate(
            year = year.toInt(),
            month = Month.valueOf(month.uppercase()),
            dayOfMonth = day.toInt(),
        )
    }

    fun toDateString(localDate: LocalDate): String = with(localDate) {
        "${translateEnglishMonth(month)} $dayOfMonth, $year"
    }

    private fun translateEnglishMonth(month: Month) =
        month.name.lowercase().replaceFirstChar { if (it.isLowerCase()) it.titlecase() else "$it" }
}
