package com.team23.view.handler

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.neuracrsrecipes.model.uimodel.SnackbarResultUiModel
import com.team23.view.extension.getStringResource

class ComposeSnackbarHandler(
    private val snackbarHostState: SnackbarHostState,
): SnackbarHandler {

    override suspend fun showStartLoading() {
        snackbarHostState.currentSnackbarData?.dismiss()
        snackbarHostState.showSnackbar(
            message = getStringResource("snackbar_start_loading"),
            duration = SnackbarDuration.Indefinite,
        )
    }

    override suspend fun showLoadingRecipe(recipeCount: Int): SnackbarResultUiModel {
        snackbarHostState.currentSnackbarData?.dismiss()
        return when (recipeCount) {
            0 -> SnackbarResult.Dismissed
            1 -> snackbarHostState.showSnackbar(
                message = getStringResource("snackbar_loading_one"),
                duration = SnackbarDuration.Indefinite,
            )

            else -> snackbarHostState.showSnackbar(
                message = getStringResource("snackbar_loading_several", "$recipeCount"),
                duration = SnackbarDuration.Indefinite,
            )
        }.toUiModel()
    }

    override suspend fun showLoadingEnded(): SnackbarResultUiModel {
        snackbarHostState.currentSnackbarData?.dismiss()
        return snackbarHostState.showSnackbar(
            message = getStringResource("snackbar_loading_done"),
            duration = SnackbarDuration.Short,
        ).toUiModel()
    }

    override suspend fun showFavoriteMessage(recipeTitle: String) : SnackbarResultUiModel {
        snackbarHostState.currentSnackbarData?.dismiss()
        return snackbarHostState.showSnackbar(
            message = "$recipeTitle ${getStringResource("snackbar_favorite")}",
            actionLabel = getStringResource("snackbar_favorite_action").uppercase(),
            duration = SnackbarDuration.Short,
        ).toUiModel()
    }

    override suspend fun showLocalPhoneMessage() {
        snackbarHostState.showSnackbar(
            message = getStringResource("recipe_local_phone_info"),
            duration = SnackbarDuration.Short,
        )
    }


    override suspend fun showRecipeHasBeenSaved(recipeTitle: String): SnackbarResultUiModel {
        snackbarHostState.currentSnackbarData?.dismiss()
        val messageEnd = getStringResource("add_recipe_save_snackbar_message")
        return snackbarHostState.showSnackbar(
            message = "$recipeTitle $messageEnd",
            duration = SnackbarDuration.Short,
            actionLabel = getStringResource("add_recipe_save_snackbar_action").uppercase(),
        ).toUiModel()
    }

    override suspend fun showRecipeHasBeenDeleted(recipeTitle: String) {
        snackbarHostState.showSnackbar(
            message = getStringResource("snackbar_recipe_deleted, recipeTitle"),
            duration = SnackbarDuration.Short,
        )
    }

    override suspend fun showRecipeHasNotBeenDeleted(recipeTitle: String) {
        snackbarHostState.showSnackbar(
            message = getStringResource("snackbar_recipe_not_deleted, recipeTitle"),
            duration = SnackbarDuration.Short,
        )
    }

    private fun SnackbarResult.toUiModel(): SnackbarResultUiModel = when(this) {
        SnackbarResult.Dismissed -> SnackbarResultUiModel.Dismissed
        SnackbarResult.ActionPerformed -> SnackbarResultUiModel.ActionPerformed
    }
}
