package com.team23.view.ds.scaffold

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun Snackbar(
	snackbarData: SnackbarData,
	modifier: Modifier = Modifier,
) {
	Snackbar(
		action = snackbarData.visuals.actionLabel?.let { actionLabel ->
			{
				TextButton(
					colors = ButtonDefaults.textButtonColors(contentColor = SnackbarDefaults.actionColor),
					onClick = { snackbarData.performAction() }
				) {
					Text(
						text = actionLabel,
						style = MaterialTheme.typography.bodySmall,
					)
				}
			}
		} ?: snackbarData.visuals.duration.takeIf { it == SnackbarDuration.Indefinite }?.let {
			{
				CircularProgressIndicator(
					color = SnackbarDefaults.actionColor,
					modifier = Modifier.padding(all = 8.dp)
				)
			}
		},
		modifier = modifier.padding(12.dp),
	) {
		Text(
			text = snackbarData.visuals.message,
			style = MaterialTheme.typography.labelLarge,
		)
	}
}
