package com.team23.view.navigation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.uimodel.SearchUiModel
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagsRowUiModel
import com.team23.neuracrsrecipes.model.uimodel.TextFieldUiModel
import com.team23.neuracrsrecipes.viewmodel.SearchViewModel
import com.team23.view.LocalTitle
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.mapper.RecipeUiMapper
import com.team23.view.navigation.AppNavigator
import com.team23.view.search_textfield_label
import com.team23.view.search_textfield_placeholder
import com.team23.view.widget.search.SearchTagsRow
import com.team23.view.widget.search.SearchTextField
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

internal data class SearchScreen(
    val selectedTag: String?,
) : Screen {

    @Composable
    override fun Content() {
        val searchViewModel = koinInject<SearchViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow
        val onRecipeClick = { recipe: SummarizedRecipeUiModel ->
            appNavigator.navigateToRecipe(navigator, recipe.id)
        }

        LocalTitle.current.value = null
        searchViewModel.selectedTag = selectedTag

        SearchScreen(
            searchUiModel = SearchUiModel(
                textField = TextFieldUiModel(
                    searchValue = searchViewModel.searchValue.value,
                    onValueChange = { newValue -> searchViewModel.onValueChange(newValue) },
                    label = stringResource(Res.string.search_textfield_label),
                    placeholder = stringResource(Res.string.search_textfield_placeholder),
                    leadingIcon = IconProperty.Vector(Icons.Filled.Search),
                ),
                tagsRow = TagsRowUiModel(
                    tags = searchViewModel.tags.collectAsState().value,
                    onTagSelected = { tag -> searchViewModel.onTagSelected(tag) },
                ),
                recipes = searchViewModel.recipes.collectAsState().value,
                onRecipeClick = onRecipeClick,
                onFavoriteClick = searchViewModel::favoriteClick,
                onLocalPhoneClick = searchViewModel::onLocalPhoneClick,
            ),
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchScreen(
    searchUiModel: SearchUiModel,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(64.dp + 16.dp))
        SearchTextField(searchUiModel.textField)

        SearchTagsRow(searchUiModel.tagsRow)

        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(searchUiModel.recipes) { recipe ->
                Cell(
                    cellProperty = RecipeUiMapper().toCellProperty(
                        recipe = recipe,
                        displayType = DisplayType.List,
                        onFavoriteClick = {
                            searchUiModel.onFavoriteClick(recipe)
                        },
                        onLocalPhoneClick = searchUiModel.onLocalPhoneClick,
                    ),
                    modifier = Modifier
                        .animateItemPlacement()
                        .clickable {
                            searchUiModel.onRecipeClick(recipe)
                        }
                )
            }
        }
    }
}
