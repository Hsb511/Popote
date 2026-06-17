package com.team23.neuracrsrecipes.model.action

import com.team23.neuracrsrecipes.model.uimodel.TagUiModel

sealed interface SearchAction {
    data class SearchValueChange(val value: String) : SearchAction
    data class TagClick(val tag: TagUiModel) : SearchAction
    data class RecipeClick(val recipeId: String) : SearchAction
    data class FavoriteClick(val recipeId: String, val recipeTitle: String) : SearchAction
    data object LocalPhoneClick : SearchAction
}
