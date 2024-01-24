package com.team23.data.mappers

import com.team23.domain.recipe.model.LanguageDomainModel

class LanguageMapper {
    fun toLanguageDomainModel(href: String): LanguageDomainModel =
        if (href.split(".html")[0].endsWith("_fr")) {
            LanguageDomainModel.FRENCH
        } else {
            LanguageDomainModel.ENGLISH
        }
}