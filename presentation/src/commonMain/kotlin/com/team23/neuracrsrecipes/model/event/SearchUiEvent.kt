package com.team23.neuracrsrecipes.model.event

sealed interface SearchUiEvent {
    data class NavigateToRecipe(val recipeId: String) : SearchUiEvent
}
