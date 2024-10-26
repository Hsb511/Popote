package com.team23.view.widget.drawer

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.team23.view.Res
import com.team23.view.dialog_confirm
import com.team23.view.dialog_dismiss
import com.team23.view.drawer_dialog_placeholder
import com.team23.view.drawer_dialog_text
import com.team23.view.drawer_empty_name
import com.team23.view.drawer_name_change
import com.team23.view.drawer_name_set
import com.team23.view.ds.button.ButtonTextDialog
import org.jetbrains.compose.resources.stringResource

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
                stringResource(Res.string.drawer_empty_name)
            } else {
                stringResource(Res.string.drawer_name_set, nickname)
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
    val dialogText = stringResource(Res.string.drawer_dialog_text)
    val dialogDescription = if (nickname == null) {
        dialogText
    } else {
        stringResource(Res.string.drawer_name_set, nickname) + ". " + dialogText
    }
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {
            val drawerTitleResource =
                if (nickname == null) Res.string.drawer_empty_name else Res.string.drawer_name_change
            Text(
                text = stringResource(resource = drawerTitleResource),
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
                        Text(text = stringResource(Res.string.drawer_dialog_placeholder))
                    },
                    onValueChange = { newValue ->
                        newNickname.value = newValue
                    }
                )
            }

        },
        confirmButton = {
            ButtonTextDialog(
                text = stringResource(Res.string.dialog_confirm),
                onClick = {
                    onChangeNickname(newNickname.value)
                    onDismissRequest()
                },
            )
        },
        dismissButton = {
            ButtonTextDialog(
                text = stringResource(Res.string.dialog_dismiss),
                onClick = onDismissRequest,
            )
        }
    )
}
