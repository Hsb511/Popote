package com.team23.design_system.snackbar

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun NeuracrSnackbarHost(
	snackbarHostState: SnackbarHostState,
	modifier: Modifier = Modifier,
) {
	SnackbarHost(
		hostState = snackbarHostState,
		snackbar = { snackbarData ->
			NeuracrSnackbar(
				snackbarData = snackbarData,
				modifier = modifier,
			)
		}
	)
}

@Composable
@Preview(showBackground = true)
fun SnackbarHostPreview() {
	NeuracrTheme {
		SnackbarHost(remember { SnackbarHostState() })
	}
}
