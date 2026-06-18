package com.team23.domain.recipe.model

sealed interface TagDomainModel {
    val localizedName: String

    data class CuisineRegion(
        val region: Region,
        override val localizedName: String,
    ) : TagDomainModel

    data class Normal(override val localizedName: String) : TagDomainModel

    enum class Region {
        AMERICAN,
        AMERICAN_MEXICAN,
        ALSATIAN,
        CHINESE,
        FRENCH,
        HUNGARIAN,
        ITALIAN,
        INDIAN,
        NORMAN,
        THAI,
        TURKISH,
    }
}
