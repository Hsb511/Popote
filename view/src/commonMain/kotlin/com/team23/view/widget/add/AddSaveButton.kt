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
import com.team23.view.Res
import com.team23.view.add_recipe_save_dialog_text
import com.team23.view.add_recipe_save_dialog_title
import com.team23.view.dialog_confirm
import com.team23.view.dialog_dismiss
import com.team23.view.ds.button.ButtonTextDialog
import com.team23.view.ds.icon.NeuracrIcon
import com.team23.view.ic_save
import org.jetbrains.compose.resources.stringResource

@Composable
fun AddSaveButton(onSaveButtonClick: () -> Unit) {
    val openDialog = remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = { openDialog.value = true },
        modifier = Modifier.size(56.dp),
    ) {
        NeuracrIcon(
            iconProperty = IconProperty.Resource(
                drawableResource = Res.drawable.ic_save,
            ),
            modifier = Modifier.size(24.dp),
        )
    }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = {
                Text(
                    text = stringResource(Res.string.add_recipe_save_dialog_title),
                    textAlign = TextAlign.Center
                )
            },
            text = { Text(stringResource(Res.string.add_recipe_save_dialog_text)) },
            confirmButton = {
                ButtonTextDialog(
                    text = stringResource(Res.string.dialog_confirm),
                    onClick = {
                        openDialog.value = false
                        onSaveButtonClick()
                    },
                )
            },
            dismissButton = {
                ButtonTextDialog(
                    text = stringResource(Res.string.dialog_dismiss),
                    onClick = {
                        openDialog.value = false
                    },
                )
            }
        )
    }
}
