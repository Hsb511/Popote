package com.team23.view.widget.add

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.ds.icon.NeuracrIcon
import com.team23.view.ic_tag

@Composable
fun AddTagChip(
    tags: MutableState<List<String>>,
    allTags: List<String>,
    onAddTag: (String) -> Unit,
) {
    val expanded = remember { mutableStateOf(false) }

    OutlinedButton(
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.onSecondaryContainer),
        shape = MaterialTheme.shapes.medium,
        onClick = {
            expanded.value = true
        },
        modifier = Modifier.width(80.dp)
    ) {
        NeuracrIcon(
            iconProperty = IconProperty.Resource(
                drawableResource = Res.drawable.ic_tag,
                contentDescription = "Add",
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
            ),
        )
    }
    TagMenu(
        allTags = allTags,
        onAddTag = { newTag ->
            onAddTag(newTag)
            tags.value += newTag
        },
        expanded = expanded,
    )
}


@Composable
private fun TagMenu(
    allTags: List<String>,
    onAddTag: (String) -> Unit,
    expanded: MutableState<Boolean>,
) {
    DropdownMenu(
        expanded = expanded.value,
        onDismissRequest = { expanded.value = false }
    ) {
        allTags.forEach { tag ->
            DropdownMenuItem(
                text = { Text(tag) },
                onClick = { onAddTag(tag) }
            )
        }
    }
}
