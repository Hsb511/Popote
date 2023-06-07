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
}
