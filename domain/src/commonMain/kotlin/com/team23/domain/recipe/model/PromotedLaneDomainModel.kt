package com.team23.domain.recipe.model

data class PromotedLaneDomainModel(
    val type: Type,
    val recipes: List<RecipeDomainModel.Summarized>,
) {
    enum class Type {
        Seasonal,
        Vegetarian,
        Vegan,
    }
}
