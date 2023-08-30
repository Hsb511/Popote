package com.team23.presentation.add.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.presentation.R

@Composable
fun AddSaveButton(onSaveButtonClick: () -> Unit) {
	val openDialog = remember { mutableStateOf(false) }

	FloatingActionButton(
		onClick = { openDialog.value = true },
		modifier = Modifier.size(56.dp),
	) {
		Icon(
			painter = painterResource(id = R.drawable.ic_save),
			contentDescription = null,
			modifier = Modifier.size(24.dp),
		)
	}

	if (openDialog.value) {
		AlertDialog(
			onDismissRequest = { openDialog.value = false },
			title = { Text(stringResource(id = R.string.add_recipe_save_dialog_title))},
			text = { Text(stringResource(id = R.string.add_recipe_save_dialog_text)) },
			confirmButton = {
				TextButton(
					onClick = {
						openDialog.value = false
						onSaveButtonClick()
					}
				) { Text(stringResource(id = R.string.dialog_confirm)) }
			},
			dismissButton = {
				TextButton(
					onClick = { openDialog.value = false }
				) { Text(stringResource(id = R.string.dialog_dismiss)) }
			}
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
