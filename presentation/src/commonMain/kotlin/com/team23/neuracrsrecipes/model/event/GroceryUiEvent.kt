package com.team23.neuracrsrecipes.model.event

sealed interface GroceryUiEvent {
    data class OpenRecipe(val recipeId: String): GroceryUiEvent
}