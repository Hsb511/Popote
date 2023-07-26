package com.team23.presentation.add.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun AddImageButton(modifier: Modifier = Modifier) {
	OutlinedButton(
		colors = ButtonDefaults.outlinedButtonColors(
			contentColor = MaterialTheme.colorScheme.tertiary,
		),
		border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.primary),
		shape = MaterialTheme.shapes.medium,
		onClick = { },
		modifier = modifier.aspectRatio(ratio = 4 / 3f, matchHeightConstraintsFirst = true)
	) {
		Icon(
			imageVector = Icons.Filled.Add,
			contentDescription = null,
		)
	}
}

@Composable
@Preview(showBackground = true)
fun AddImageButtonPreview() {
	NeuracrTheme {
		AddImageButton()
	}
}
