package com.team23.data.mappers

import kotlinx.datetime.LocalDate

class SubtitleMapper(
	private val dateMapper: DateMapper,
) {
	fun toAuthorDomainModel(subtitle: String): String =
		subtitle.split(SUBTITLE_DELIMITER).last()

	fun toSubtitleDataModel(date: LocalDate, author: String): String =
		dateMapper.toDateString(date) + SUBTITLE_DELIMITER + author
}

private const val SUBTITLE_DELIMITER = " - Written by "
