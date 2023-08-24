package com.team23.data.mappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateMapper @Inject constructor() {
    companion object {
        private const val RAW_DATE_FORMAT = "yyyy/MM/dd"
        private const val SUBTITLE_DATE_FORMAT = "MMMM dd, yyyy"
    }

    fun toLocalDate(dateStr: String): LocalDate =
        LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(RAW_DATE_FORMAT))

    fun toDateString(localDate: LocalDate): String =
        localDate.format(DateTimeFormatter.ofPattern(SUBTITLE_DATE_FORMAT))
}
