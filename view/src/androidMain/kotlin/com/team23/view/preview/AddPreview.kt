package com.team23.view.preview

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.model.uimodel.AddRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.RecipeUiModel
import com.team23.view.navigation.screen.AddScreen
import com.team23.view.sample.property.resourceImagePreviewSample
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.add.AddImageButton
import com.team23.view.widget.add.AddSaveButton
import com.team23.view.widget.add.AddTagChip
import com.team23.view.widget.add.AddTagSection
import com.team23.view.widget.add.AddTextField

@Composable
@Preview(showBackground = true)
fun AddImageButtonPreview() {
    PopoteTheme {
        AddImageButton(ImageProperty.None)
    }
}

@Composable
@Preview(showBackground = true)
fun AddSaveButtonPreview() {
    PopoteTheme {
        AddSaveButton {}
    }
}

@Composable
@Preview(showBackground = true)
fun AddTagChipPreview() {
    PopoteTheme {
        AddTagChip(
            tags = remember { mutableStateOf(emptyList()) },
            allTags = listOf(),
            onAddTag = {})
    }
}


@Composable
@Preview(showBackground = true)
fun AddTagSectionPreview() {
    PopoteTheme {
        AddTagSection(allTags = emptyList(), onAddTag = {}, onRemoveTag = {})
    }
}


@Composable
@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
fun AddTextFieldPreview() {
    PopoteTheme {
        AddTextField(
            text = "value",
            onTextChange = {},
            placeholder = "value",
            style = MaterialTheme.typography.displayLarge,
            singleLine = false,
        )
    }
}


@Composable
@Preview(showSystemUi = true)
fun AddScreenPreview() {
    PopoteTheme {
        AddScreen(
            addRecipe = AddRecipeUiModel(
                recipe = RecipeUiModel(
                    id = "convenire",
                    title = "quo",
                    date = "varius",
                    author = "sed",
                    tags = listOf("a", "b", "cccc"),
                    image = resourceImagePreviewSample,
                    ingredients = listOf(),
                    instructions = listOf(),
                    defaultServingsAmount = 5231,
                    description = "quisque",
                    conclusion = "mandamus",
                    isFavorite = true,
                    isLocallySaved = true
                ),
            ),
            scrollState = rememberScrollState(),
            heightToBeFaded = remember { mutableFloatStateOf(120f) },
            allTags = listOf("a", "bbbb", "23232323"),
        )
    }
}
