package com.team23.view.preview

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.team23.neuracrsrecipes.model.uimodel.SearchUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagsRowUiModel
import com.team23.view.screen.SearchScreen
import com.team23.view.sample.uimodel.previewTextFieldSample
import com.team23.view.widget.search.SearchRecipeCard
import com.team23.view.sample.uimodel.summarizedRecipeSample
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.search.SearchFilterChip
import com.team23.view.widget.search.SearchTagsRow
import com.team23.view.widget.search.SearchTextField


@Composable
@Preview(showBackground = true)
fun SearchFilterChipPreview() {
    PopoteTheme {
        SearchFilterChip(
            TagUiModel("soup", true)
        ) {}
    }
}

@Composable
@Preview(showBackground = true)
fun SearchRecipeCardPreview() {
    PopoteTheme {
        SearchRecipeCard(summarizedRecipeSample, { })
    }
}

@Composable
@Preview(showSystemUi = true)
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
                onTagSelected = { },
            )
        )
    }
}


@Composable
@Preview(showBackground = true)
fun SearchTextFieldPreview() {
    PopoteTheme {
        SearchTextField(
            textFieldUiModel = previewTextFieldSample,
            modifier = Modifier.background(Color.White),
        )
    }
}

@Composable
@Preview(showSystemUi = true)
private fun SearchScreenPreview() {
    PopoteTheme {
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
                    onTagSelected = { },
                ),
                recipes = List(6) { summarizedRecipeSample },
                onRecipeClick = {},
                onFavoriteClick = {},
                onLocalPhoneClick = {},
            ),
            modifier = Modifier.background(color = MaterialTheme.colorScheme.background)
        )
    }
}
