package com.team23.presentation.add.views

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.button.DialogTextButton
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
			title = {
				Text(
					text = stringResource(id = R.string.add_recipe_save_dialog_title),
					textAlign = TextAlign.Center
				)
			},
			text = { Text(stringResource(id = R.string.add_recipe_save_dialog_text)) },
			confirmButton = {
				DialogTextButton(
					text = stringResource(id = R.string.dialog_confirm),
					onClick = {
						openDialog.value = false
						onSaveButtonClick()
					},
				)
			},
			dismissButton = {
				DialogTextButton(
					text = stringResource(id = R.string.dialog_dismiss),
					onClick = {
						openDialog.value = false
					},
				)
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
