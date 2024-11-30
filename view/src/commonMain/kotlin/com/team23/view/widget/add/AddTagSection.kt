package com.team23.view.widget.add

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

@Composable
fun AddTagSection(
    allTags: List<String>,
    onAddTag: (String) -> Unit,
    onRemoveTag: (String) -> Unit,
) {
    val tags = remember { mutableStateOf(emptyList<String>()) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        item {
            AddTagChip(tags = tags, allTags = allTags, onAddTag = onAddTag)
        }
        items(tags.value) { tag ->
            ElevatedSuggestionChip(
                colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    labelColor = MaterialTheme.colorScheme.onTertiary,
                ),
                onClick = {
                    onRemoveTag(tag)
                    val currentTags = tags.value.toMutableList()
                    currentTags.remove(tag)
                    tags.value = currentTags
                },
                label = {
                    Text(text = tag)
                }
            )
        }
    }
}
