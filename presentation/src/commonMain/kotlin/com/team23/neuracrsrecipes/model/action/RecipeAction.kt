package com.team23.neuracrsrecipes.model.action

import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel

sealed interface RecipeAction {
    data class UpdateServingsAmount(val newAmount: String) : RecipeAction
    data object AddOneServing : RecipeAction
    data object SubtractOneServing : RecipeAction
    data class ToggleFavorite(val recipe: RecipeUiModel) : RecipeAction
    data object ShowLocalPhoneMessage : RecipeAction
}
