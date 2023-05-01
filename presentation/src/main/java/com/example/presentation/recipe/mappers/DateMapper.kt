package com.example.presentation.recipe.mappers

import com.example.domain.models.LanguageDomainModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateMapper @Inject constructor() {
	companion object {
		private const val ENGLISH_RAW_DATE_FORMAT = "MMMM dd, uuuu"
		private const val FRENCH_RAW_DATE_FORMAT = "dd MMMM uuuu"
	}

	fun toSubtitleDate(localDate: LocalDate, language: LanguageDomainModel): String =
		if (language == LanguageDomainModel.FRENCH) {
			localDate.format(DateTimeFormatter.ofPattern(FRENCH_RAW_DATE_FORMAT))
		} else {
			localDate.format(DateTimeFormatter.ofPattern(ENGLISH_RAW_DATE_FORMAT))
		}
}