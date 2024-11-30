package com.team23.view.ds.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ButtonTextDialog(text: String, onClick: () -> Unit) {
	TextButton(
		onClick = onClick
	) {
		Text(
			text = text,
			color = MaterialTheme.colorScheme.onSecondaryContainer,
			style = MaterialTheme.typography.labelLarge,
			fontWeight = FontWeight.Normal,
		)
	}
}
