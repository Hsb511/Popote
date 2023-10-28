package com.team23.presentation.drawer.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.button.DialogTextButton
import com.team23.presentation.R

@Composable
fun NicknameDrawerItem(
	nickname: String?,
	onChangeNickname: (String) -> Unit,
	modifier: Modifier = Modifier,
) {
	var isDialogVisible by remember { mutableStateOf(false) }
	NavigationDrawerItem(
		label = {
			val text = if (nickname == null) {
				stringResource(id = R.string.drawer_empty_name)
			} else {
				stringResource(id = R.string.drawer_name_set, nickname)
			}
			Text(text = text)
		},
		icon = {
			Icon(imageVector = Icons.Filled.Face, contentDescription = null)
		},
		selected = false,
		colors = NavigationDrawerItemDefaults.colors(
			unselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant
		),
		onClick = { isDialogVisible = true },
		modifier = modifier,
	)

	if (isDialogVisible) {
		NicknameDialog(
			nickname = nickname,
			onChangeNickname = onChangeNickname,
			onDismissRequest = { isDialogVisible = false },
		)
	}

}

@Composable
private fun NicknameDialog(
	nickname: String?,
	onDismissRequest: () -> Unit,
	onChangeNickname: (String) -> Unit,
) {
	val newNickname = remember { mutableStateOf(nickname ?: "") }
	val dialogText = stringResource(id = R.string.drawer_dialog_text)
	val dialogDescription = if (nickname == null) {
		dialogText
	} else {
		stringResource(id = R.string.drawer_name_set, nickname) + ". " + dialogText
	}
	AlertDialog(
		onDismissRequest = onDismissRequest,
		title = {
			val text =
				stringResource(id = if (nickname == null) R.string.drawer_empty_name else R.string.drawer_name_change)
			Text(
				text = text,
				textAlign = TextAlign.Center
			)
		},
		text = {
			Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
				Text(text = dialogDescription)
				OutlinedTextField(
					value = newNickname.value,
					singleLine = true,
					label = {
						Text(text = stringResource(id = R.string.drawer_dialog_placeholder))
					},
					onValueChange = { newValue ->
						newNickname.value = newValue
					}
				)
			}

		},
		confirmButton = {
			DialogTextButton(
				text = stringResource(id = R.string.dialog_confirm),
				onClick = {
					onChangeNickname(newNickname.value)
					onDismissRequest()
				},
			)
		},
		dismissButton = {
			DialogTextButton(
				text = stringResource(id = R.string.dialog_dismiss),
				onClick = onDismissRequest,
			)
		}
	)
}

@Composable
@Preview(showBackground = true)
fun NicknameDrawerItemPreview() {
	MaterialTheme {
		NicknameDrawerItem("GOGO", {})
	}
}
