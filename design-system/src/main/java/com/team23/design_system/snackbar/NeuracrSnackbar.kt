package com.team23.design_system.snackbar

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
internal fun NeuracrSnackbar(
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
		},
		modifier = modifier.padding(12.dp),
	) {
		Text(
			text = snackbarData.visuals.message,
			style = MaterialTheme.typography.labelLarge,
		)
	}
}

@Composable
@Preview(showBackground = true)
fun SnackbarPreview() {
	NeuracrTheme {
		NeuracrSnackbar(
			snackbarData = object : SnackbarData {
				override val visuals = object : SnackbarVisuals {
					override val message = "Tomato soup has been added to your favorites"
					override val actionLabel: String = "UNDO"
					override val duration = SnackbarDuration.Indefinite
					override val withDismissAction = false
				}

				override fun dismiss() {}
				override fun performAction() {}
			}
		)
	}
}
