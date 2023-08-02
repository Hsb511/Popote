package com.team23.presentation.add.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.presentation.R

@Composable
fun AddSaveButton(
	onSaveButtonClick: () -> Unit,
) {
	FloatingActionButton(
		containerColor = MaterialTheme.colorScheme.secondary,
		contentColor = MaterialTheme.colorScheme.onSurface,
		onClick = onSaveButtonClick,
		modifier = Modifier.size(56.dp),
	) {
		Icon(
			painter = painterResource(id = R.drawable.ic_save),
			contentDescription = null,
			modifier = Modifier.size(24.dp),
		)
	}
}

@Composable
@Preview(showBackground = true)
fun AddSaveButtonPreview() {
	MaterialTheme {
		AddSaveButton {}
	}
}
