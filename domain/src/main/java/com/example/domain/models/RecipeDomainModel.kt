package com.example.domain.models

import java.time.LocalDate

sealed class RecipeDomainModel(
    open val id: String,
    open val title: String,
    open val imageUrl: String,
    open val date: LocalDate,
    open val language: LanguageDomainModel,
) {
    data class Summarized(
        override val id: String,
        override val title: String,
        override val imageUrl: String,
        override val date: LocalDate,
        override val language: LanguageDomainModel,
    ) : RecipeDomainModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        date = date,
        language = language,
    )

    data class Full(
        override val id: String,
        override val title: String,
        override val imageUrl: String,
        override val date: LocalDate,
        override val language: LanguageDomainModel,
        val author: String,
        val tags: List<String>,
        val servingsNumber: Int,
        val ingredients: List<IngredientDomainModel>,
        val startingText: String,
        val instructions: List<InstructionDomainModel>,
        val endingText: String,
        val sections: List<SectionDomainModel>,
    ) : RecipeDomainModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        date = date,
        language = language,
    )
}
