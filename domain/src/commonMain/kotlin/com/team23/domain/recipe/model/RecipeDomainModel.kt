package com.team23.domain.recipe.model

import kotlinx.datetime.LocalDate


sealed class RecipeDomainModel(
    open val id: String,
    open val title: String,
    open val imageUrl: String,
    open val date: LocalDate,
    open val language: LanguageDomainModel,
    open val isFavorite: Boolean,
    open val source: Source,
    open val isInGroceryList: Boolean,
) {
    data class Summarized(
        override val id: String,
        override val title: String,
        override val imageUrl: String,
        override val date: LocalDate,
        override val language: LanguageDomainModel,
        override val isFavorite: Boolean,
        override val source: Source,
        override val isInGroceryList: Boolean,
    ) : RecipeDomainModel(
        id = id,
        title = title,
        imageUrl = imageUrl,
        date = date,
        language = language,
        isFavorite = isFavorite,
        source = source,
        isInGroceryList = isInGroceryList
    )

    data class Full(
        override val id: String,
        override val title: String,
        override val imageUrl: String,
        override val date: LocalDate,
        override val language: LanguageDomainModel,
        override val isFavorite: Boolean,
        override val source: Source,
        override val isInGroceryList: Boolean,
        val author: String,
        val tags: List<TagDomainModel>,
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
        source = source,
        isInGroceryList = isInGroceryList,
    )

    sealed class Source {
        data object Remote: Source()
        sealed class Local: Source() {
            data object Saved: Local()
            data object Temporary: Local()
        }
    }
}
