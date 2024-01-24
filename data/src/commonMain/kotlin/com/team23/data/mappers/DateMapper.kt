package com.team23.data.mappers

import kotlinx.datetime.LocalDate

class DateMapper {

    fun toLocalDateFromHrefDate(dateStr: String): LocalDate =
        // TODO
        // LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(RAW_DATE_FORMAT))
        LocalDate.fromEpochDays(23)

    fun toLocalDateFromSubtitleDate(dateStr: String): LocalDate =
        // TODO
        // LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(SUBTITLE_DATE_FORMAT, Locale.ENGLISH))
        LocalDate.fromEpochDays(23)

    fun toDateString(localDate: LocalDate): String =
        // TODO
        // localDate.format(DateTimeFormatter.ofPattern(SUBTITLE_DATE_FORMAT, Locale.ENGLISH))
        "23"
}

private const val RAW_DATE_FORMAT = "yyyy/MM/dd"
private const val SUBTITLE_DATE_FORMAT = "MMMM dd, yyyy"
