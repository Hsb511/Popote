package com.team23.neuracrsrecipes.model.uimodel

import androidx.compose.runtime.Immutable

@Immutable
data class PromotedLaneUiModel(
    val type: Type,
    val recipes: List<SummarizedRecipeUiModel>,
) {
    enum class Type {
        Seasonal, Vegetarian, Vegan
    }
}
