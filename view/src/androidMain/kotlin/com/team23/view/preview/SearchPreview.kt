package com.team23.view.preview

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.neuracrsrecipes.model.uimodel.SearchUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagsRowUiModel
import com.team23.view.mapper.RecipeUiMapper
import com.team23.view.navigation.screen.SearchScreen
import com.team23.view.sample.uimodel.previewTextFieldSample
import com.team23.view.sample.uimodel.summarizedRecipeSample
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.search.SearchFilterChip
import com.team23.view.widget.search.SearchTagsRow
import com.team23.view.widget.search.SearchTextField

@Composable
@PreviewWithBackground
fun SearchFilterChipPreview() {
    PopoteTheme {
        SearchFilterChip(
            TagUiModel.Label("soup", true)
        ) {}
    }
}

@Composable
@PreviewWithBackground
fun SearchTagsRowPreview() {
    PopoteTheme {
        SearchTagsRow(
            tagsRowUiModel = TagsRowUiModel(
                tags = tags,
            )
        )
    }
}


@Composable
@PreviewWithBackground
fun SearchTextFieldPreview() {
    PopoteTheme {
        SearchTextField(
            textFieldUiModel = previewTextFieldSample,
            modifier = Modifier.background(Color.White),
        )
    }
}

@Composable
@PreviewWithSystemUi
private fun SearchScreenPreview() {
    PopoteTheme {
        val recipeUiMapper = remember { RecipeUiMapper() }
        SearchScreen(
            searchUiModel = SearchUiModel(
                textField = previewTextFieldSample,
                tagsRow = TagsRowUiModel(
                    tags = tags,
                ),
                items = List(6) { summarizedRecipeSample }.mapIndexed { id, recipe ->
                    SearchUiModel.Item(
                        id = "$id",
                        cellProperty = recipeUiMapper.toCellProperty(
                            recipe = recipe,
                            displayType = DisplayType.List,
                        ),
                    )
                }
            ),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        )
    }
}

private val tags = listOf(
    TagUiModel.Flag("", true, FlagProperty.ITALIAN),
    TagUiModel.Label("soup", true),
    TagUiModel.Label("veggie", true),
    TagUiModel.Label("cocktail", false),
    TagUiModel.Label("drink", false),
    TagUiModel.Label("main", false),
    TagUiModel.Label("italian", true)
)