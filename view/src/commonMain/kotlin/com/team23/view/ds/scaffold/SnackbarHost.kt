package com.team23.view.ds.scaffold

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NeuracrSnackbarHost(
	snackbarHostState: SnackbarHostState,
	modifier: Modifier = Modifier,
) {
	SnackbarHost(
		hostState = snackbarHostState,
		snackbar = { snackbarData ->
			Snackbar(
				snackbarData = snackbarData,
				modifier = modifier,
			)
		}
	)
}
