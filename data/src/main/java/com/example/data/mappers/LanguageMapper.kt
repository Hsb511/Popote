package com.example.data.mappers

import com.example.domain.models.LanguageDomainModel
import javax.inject.Inject

class LanguageMapper @Inject constructor() {
	fun toLanguageDomainModel(href: String): LanguageDomainModel =
		if (href.split(".html")[0].endsWith("_fr")) {
			LanguageDomainModel.FRENCH
		} else {
			LanguageDomainModel.ENGLISH
		}
}