package com.team23.neuracrsrecipes.mapper

import com.team23.neuracrsrecipes.extension.getLocalLanguage
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateUiMapper {

    fun toSubtitleDate(localDate: LocalDate): String =
        with(localDate) {
            if (getLocalLanguage() == "fr") {
                "$dayOfMonth ${toFrenchStringMonth(month)} $year"
            } else {
                "${month.name.lowercase().replaceFirstChar { it.uppercase() }} $dayOfMonth, $year"
            }
        }

    fun toLocalDate(stringDate: String): LocalDate = when {
        stringDate.matches(Regex(FRENCH_RAW_DATE_FORMAT)) -> stringDate.split(" ").let {
            val year = it[2].toInt()
            val month = toMonth(it[1])
            val day = it[0].toInt()
            LocalDate(year = year, month = month, dayOfMonth = day)
        }

        stringDate.matches(Regex(ENGLISH_RAW_DATE_FORMAT)) -> stringDate.split(" ").let {
            val year = it[2].toInt()
            val month = Month.valueOf(it[0].uppercase())
            val day = it[1].split(",")[0].toInt()
            LocalDate(year = year, month = month, dayOfMonth = day)
        }

        else -> Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    private fun toFrenchStringMonth(month: Month): String = when (month) {
        Month.JANUARY -> FRENCH_JANUARY
        Month.FEBRUARY -> FRENCH_FEBRUARY
        Month.MARCH -> FRENCH_MARCH
        Month.APRIL -> FRENCH_APRIL
        Month.MAY -> FRENCH_MAY
        Month.JUNE -> FRENCH_JUNE
        Month.JULY -> FRENCH_JULY
        Month.AUGUST -> FRENCH_AUGUST
        Month.SEPTEMBER -> FRENCH_SEPTEMBER
        Month.OCTOBER -> FRENCH_OCTOBER
        Month.NOVEMBER -> FRENCH_NOVEMBER
        Month.DECEMBER -> FRENCH_DECEMBER
        else -> throw IllegalArgumentException()
    }

    private fun toMonth(frenchStringMonth: String): Month = when (frenchStringMonth) {
        FRENCH_JANUARY -> Month.JANUARY
        FRENCH_FEBRUARY -> Month.FEBRUARY
        FRENCH_MARCH -> Month.MARCH
        FRENCH_APRIL -> Month.APRIL
        FRENCH_MAY -> Month.MAY
        FRENCH_JUNE -> Month.JUNE
        FRENCH_JULY -> Month.JULY
        FRENCH_AUGUST -> Month.AUGUST
        FRENCH_SEPTEMBER -> Month.SEPTEMBER
        FRENCH_OCTOBER -> Month.OCTOBER
        FRENCH_NOVEMBER -> Month.NOVEMBER
        FRENCH_DECEMBER -> Month.DECEMBER
        else -> throw IllegalArgumentException()
    }
}

private const val FRENCH_RAW_DATE_FORMAT = "dd MMMM uuuu"
private const val ENGLISH_RAW_DATE_FORMAT = "MMMM dd, uuuu"

private const val FRENCH_JANUARY = "janvier"
private const val FRENCH_FEBRUARY = "février"
private const val FRENCH_MARCH = "mars"
private const val FRENCH_APRIL = "avril"
private const val FRENCH_MAY = "mai"
private const val FRENCH_JUNE = "juin"
private const val FRENCH_JULY = "juillet"
private const val FRENCH_AUGUST = "août"
private const val FRENCH_SEPTEMBER = "septembre"
private const val FRENCH_OCTOBER = "octobre"
private const val FRENCH_NOVEMBER = "novembre"
private const val FRENCH_DECEMBER = "décembre"
