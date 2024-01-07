package com.team23.neuracrsrecipes.mapper

import com.team23.domain.recipe.model.LanguageDomainModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateMapper {

	fun toSubtitleDate(localDate: LocalDate, language: LanguageDomainModel): String = with(localDate) {
		if (language == LanguageDomainModel.FRENCH) {
			"$dayOfMonth $month $year"
		} else {
			"$month $dayOfMonth, $year"
		}
	}

	fun toLocalDate(stringDate: String): LocalDate = when {
		stringDate.matches(Regex(FRENCH_RAW_DATE_FORMAT)) -> stringDate.split(" ").let {
			val year = it[2].toInt()
			val month = it[0].toInt()
			val day =  it[1].split(",")[0].toInt()
			LocalDate(year = year, monthNumber = month, dayOfMonth = day)
		}
		stringDate.matches(Regex(ENGLISH_RAW_DATE_FORMAT)) -> stringDate.split(" ").let {
			val year = it[2].toInt()
			val month = it[1].toInt()
			val day = it[0].toInt()
			LocalDate(year = year, monthNumber = month, dayOfMonth = day)
		}
		else -> Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
	}
}

private const val ENGLISH_RAW_DATE_FORMAT = "MM dd, uuuu"
private const val FRENCH_RAW_DATE_FORMAT = "dd MM uuuu"
