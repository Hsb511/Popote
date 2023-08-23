package com.team23.presentation.recipe.mappers

import android.os.Build
import androidx.annotation.RequiresApi
import com.team23.domain.models.LanguageDomainModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateMapper @Inject constructor() {
	companion object {
		private const val ENGLISH_RAW_DATE_FORMAT = "MMMM dd, uuuu"
		private const val FRENCH_RAW_DATE_FORMAT = "dd MMMM uuuu"
	}

	@RequiresApi(Build.VERSION_CODES.O)
	fun toSubtitleDate(localDate: LocalDate, language: LanguageDomainModel): String =
		if (language == LanguageDomainModel.FRENCH) {
			localDate.format(DateTimeFormatter.ofPattern(FRENCH_RAW_DATE_FORMAT))
		} else {
			localDate.format(DateTimeFormatter.ofPattern(ENGLISH_RAW_DATE_FORMAT))
		}

	@RequiresApi(Build.VERSION_CODES.O)
	fun toLocalDate(stringDate: String): LocalDate = when {
		stringDate.matches(Regex(FRENCH_RAW_DATE_FORMAT)) ->
			LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(FRENCH_RAW_DATE_FORMAT))
		stringDate.matches(Regex(ENGLISH_RAW_DATE_FORMAT)) ->
			LocalDate.parse(stringDate, DateTimeFormatter.ofPattern(ENGLISH_RAW_DATE_FORMAT))
		else -> LocalDate.now()
	}
}