package com.team23.domain.models

import java.time.LocalDate

sealed class RecipeDomainModel(
    open val id: String,
    open val title: String,
    open val imageUrl: String,
    open val date: LocalDate,
    open val language: LanguageDomainModel,
    open val isFavorite: Boolean,
) {
    data class Summarized(
        override val id: String,
        override val title: String,
        override val imageUrl: String,
        override val date: LocalDate,
        override val language: LanguageDomainModel,
        override val isFavorite: Boolean,
    ) : RecipeDomainModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        date = date,
        language = language,
        isFavorite = isFavorite,
    )

    data class Full(
        override val id: String,
        override val title: String,
        override val imageUrl: String,
        override val date: LocalDate,
        override val language: LanguageDomainModel,
        override val isFavorite: Boolean,
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
        isFavorite = isFavorite,
    )
}
