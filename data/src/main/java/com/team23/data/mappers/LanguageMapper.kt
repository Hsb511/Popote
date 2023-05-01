package com.team23.data.mappers

import com.team23.domain.models.LanguageDomainModel
import javax.inject.Inject

class LanguageMapper @Inject constructor() {
	fun toLanguageDomainModel(href: String): LanguageDomainModel =
		if (href.split(".html")[0].endsWith("_fr")) {
			LanguageDomainModel.FRENCH
		} else {
			LanguageDomainModel.ENGLISH
		}
}