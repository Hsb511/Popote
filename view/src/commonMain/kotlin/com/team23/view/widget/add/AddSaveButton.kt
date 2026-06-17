package com.team23.view.widget.add

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.add_recipe_save_dialog_text
import com.team23.view.add_recipe_save_dialog_title
import com.team23.view.ds.dialog.SimpleAlertDialog
import com.team23.view.ds.icon.PopoteIcon
import com.team23.view.ic_save

@Composable
fun AddSaveButton(onSaveButtonClick: () -> Unit) {
    val openDialog = remember { mutableStateOf(false) }

    FloatingActionButton(
        onClick = { openDialog.value = true },
        modifier = Modifier.size(56.dp).offset(y = 56.dp),
    ) {
        PopoteIcon(
            iconProperty = IconProperty.Resource(
                drawableResource = Res.drawable.ic_save,
            ),
            modifier = Modifier.size(24.dp),
        )
    }

    SimpleAlertDialog(
        title = Res.string.add_recipe_save_dialog_title,
        description = Res.string.add_recipe_save_dialog_text,
        isVisible = openDialog,
        confirmButtonClick = onSaveButtonClick,
    )
}
