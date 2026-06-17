package com.team23.view.preview

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.team23.neuracrsrecipes.model.property.DisplayType
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
            TagUiModel("soup", true)
        ) {}
    }
}

@Composable
@PreviewWithBackground
fun SearchTagsRowPreview() {
    PopoteTheme {
        SearchTagsRow(
            tagsRowUiModel = TagsRowUiModel(
                tags = listOf(
                    TagUiModel("soup", true),
                    TagUiModel("veggie", true),
                    TagUiModel("cocktail", false),
                    TagUiModel("drink", false),
                    TagUiModel("main", false),
                    TagUiModel("italian", true)
                ),
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
                    tags = listOf(
                        TagUiModel("soup", true),
                        TagUiModel("veggie", true),
                        TagUiModel("cocktail", false),
                        TagUiModel("drink", false),
                        TagUiModel("main", false),
                        TagUiModel("italian", true)
                    ),
                ),
                items = List(6) { summarizedRecipeSample }.mapIndexed { id, recipe ->
                    SearchUiModel.Item(
                        id = "$id",
                        cellProperty = recipeUiMapper.toCellProperty(
                            recipe = recipe,
                            displayType = DisplayType.List,
                            onFavoriteClick = {},
                            onLocalPhoneClick = {},
                        ),
                    )
                }
            ),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        )
    }
}
