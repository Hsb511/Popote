package com.team23.presentation.common.handlers

import android.content.Context
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import com.team23.presentation.R

class SnackbarHandler(
	private val snackbarHostState: SnackbarHostState,
	private val context: Context,
) {
	suspend fun showFavoriteSnackbar(recipeTitle: String): SnackbarResult {
		snackbarHostState.currentSnackbarData?.dismiss()
		return snackbarHostState.showSnackbar(
			message = "$recipeTitle ${context.getString(R.string.snackbar_favorite)}",
			actionLabel = context.getString(R.string.snackbar_favorite_action).uppercase(),
			duration = SnackbarDuration.Short,
		)
	}

	suspend fun showLoadingRecipe(recipeCount: Int): SnackbarResult {
		snackbarHostState.currentSnackbarData?.dismiss()
		return when (recipeCount) {
			0 -> SnackbarResult.Dismissed
			1 -> snackbarHostState.showSnackbar(
				message = context.getString(R.string.snackbar_loading_one),
				duration = SnackbarDuration.Indefinite,
			)

			else -> snackbarHostState.showSnackbar(
				message = context.getString(R.string.snackbar_loading_several, recipeCount),
				duration = SnackbarDuration.Indefinite,
			)
		}
	}

	suspend fun showLoadingEnded(): SnackbarResult {
		snackbarHostState.currentSnackbarData?.dismiss()
		return snackbarHostState.showSnackbar(
			message = context.getString(R.string.snackbar_loading_done),
			duration = SnackbarDuration.Short,
		)
	}

	suspend fun showRecipeHasBeenSaved(recipeTitle: String): SnackbarResult {
		snackbarHostState.currentSnackbarData?.dismiss()
		val messageEnd = context.getString(R.string.add_recipe_save_snackbar_message)
		return snackbarHostState.showSnackbar(
			message = "$recipeTitle $messageEnd",
			duration = SnackbarDuration.Short,
			actionLabel = context.getString(R.string.add_recipe_save_snackbar_action).uppercase(),
		)
	}
}
