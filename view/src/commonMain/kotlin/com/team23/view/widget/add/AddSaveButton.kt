package com.team23.view.widget.add

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.ds.button.ButtonTextDialog
import com.team23.view.ds.icon.NeuracrIcon
import com.team23.view.extension.stringResource

@Composable
fun AddSaveButton(onSaveButtonClick: () -> Unit) {
    val openDialog = remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = { openDialog.value = true },
        modifier = Modifier.size(56.dp),
    ) {
        NeuracrIcon(
            iconProperty = IconProperty.Resource(
                fileName = "drawable/ic_save.xml",
            ),
            modifier = Modifier.size(24.dp),
        )
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                Text(
                    text = stringResource(id = "add_recipe_save_dialog_title"),
                    textAlign = TextAlign.Center
                )
            },
            text = { Text(stringResource(id = "add_recipe_save_dialog_text")) },
            confirmButton = {
                ButtonTextDialog(
                    text = stringResource(id = "dialog_confirm"),
                    onClick = {
                        openDialog.value = false
                        onSaveButtonClick()
                    },
                )
            },
            dismissButton = {
                ButtonTextDialog(
                    text = stringResource(id = "dialog_dismiss"),
                    onClick = {
                        openDialog.value = false
                    },
                )
            }
        )
    }
}
