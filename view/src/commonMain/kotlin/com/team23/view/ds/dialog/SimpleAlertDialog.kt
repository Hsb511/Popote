package com.team23.view.ds.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.team23.view.Res
import com.team23.view.dialog_confirm
import com.team23.view.dialog_dismiss
import com.team23.view.ds.button.ButtonTextDialog
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SimpleAlertDialog(
    title: StringResource,
    description: StringResource,
    isVisible: MutableState<Boolean>,
    modifier: Modifier = Modifier,
    confirmButtonLabel: StringResource = Res.string.dialog_confirm,
    confirmButtonClick: () -> Unit = {},
    dismissButtonLabel: StringResource = Res.string.dialog_dismiss,
    dismissButtonClick: () -> Unit = {},
) {
    if (isVisible.value) {
        AlertDialog(
            onDismissRequest = {
                dismissButtonClick()
                isVisible.value = false
            },
            title = {
                Text(
                    text = stringResource(title),
                    textAlign = TextAlign.Center
                )
            },
            text = { Text(stringResource(description)) },
            confirmButton = {
                ButtonTextDialog(
                    text = stringResource(confirmButtonLabel),
                    onClick = {
                        confirmButtonClick()
                        isVisible.value = false
                    },
                )
            },
            dismissButton = {
                ButtonTextDialog(
                    text = stringResource(dismissButtonLabel),
                    onClick = {
                        dismissButtonClick()
                        isVisible.value = false
                    },
                )
            },
            modifier = modifier,
        )
    }
}
