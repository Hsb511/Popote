package com.example.data.mappers

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateMapper @Inject constructor() {
    companion object {
        private const val RAW_DATE_FORMAT = "yyyy/MM/dd"
    }

    fun toLocalDate(dateStr: String): LocalDate =
        LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(RAW_DATE_FORMAT))
}
