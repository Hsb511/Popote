package com.team23.view.widget.recipe

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import com.team23.view.ds.flag.PopoteFlag

@Composable
fun RecipeTagsSection(
    tags: List<TagUiModel>,
    modifier: Modifier = Modifier,
    onTagClicked: (String) -> Unit = {},
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        items(tags) { tag ->
            RecipeTag(
                tag = tag,
                onTagClicked = onTagClicked,
            )
        }
    }
}

@Composable
private fun RecipeTag(
    tag: TagUiModel,
    onTagClicked: (String) -> Unit = {},
) {
    when (tag) {
        is TagUiModel.Label -> ElevatedSuggestionChip(
            colors = SuggestionChipDefaults.elevatedSuggestionChipColors(
                containerColor = MaterialTheme.colorScheme.tertiary,
                labelColor = MaterialTheme.colorScheme.onTertiary,
            ),
            onClick = { onTagClicked(tag.label) },
            label = {
                Text(text = tag.label)
            }
        )

        is TagUiModel.Flag -> PopoteFlag(
            flagProperty = tag.flagProperty,
            modifier = Modifier
                .height(30.dp)
                .aspectRatio(3f / 2f)
                .semantics {
                    this.contentDescription = tag.label
                }
                .clip(MaterialTheme.shapes.small)
                .clickable {
                    onTagClicked(tag.label)
                },
            )
    }
}
