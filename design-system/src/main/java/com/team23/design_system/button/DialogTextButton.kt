package com.team23.design_system.button

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight


@Composable
fun DialogTextButton(text: String, onClick: () -> Unit) {
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
