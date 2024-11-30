package com.team23.neuracrsrecipes.handler

import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel

interface SnackbarHandler {
    suspend fun showStartLoading()
    suspend fun showLoadingRecipe(recipeCount: Int): SnackbarResultUiModel
    suspend fun showLoadingEnded(): SnackbarResultUiModel
    suspend fun showFavoriteMessage(recipeTitle: String): SnackbarResultUiModel
    suspend fun showLocalPhoneMessage()
    suspend fun showRecipeHasBeenSaved(recipeTitle: String): SnackbarResultUiModel
    suspend fun showRecipeHasBeenDeleted(recipeTitle: String)
    suspend fun showRecipeHasNotBeenDeleted(recipeTitle: String)
}
