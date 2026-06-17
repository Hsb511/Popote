package com.team23.neuracrsrecipes.model.action

sealed interface HomeAction {

    data object RefreshRecipes: HomeAction
    data class ToggleFavorite(val recipeId: String, val recipeTitle: String): HomeAction
    data object ShowLocalPhoneMessage: HomeAction
}
