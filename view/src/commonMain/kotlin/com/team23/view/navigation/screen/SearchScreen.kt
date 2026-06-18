package com.team23.view.navigation.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.action.SearchAction
import com.team23.neuracrsrecipes.model.event.SearchUiEvent
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.uimodel.SearchUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagUiModel
import com.team23.neuracrsrecipes.model.uimodel.TagsRowUiModel
import com.team23.neuracrsrecipes.model.uimodel.TextFieldUiModel
import com.team23.neuracrsrecipes.viewmodel.SearchViewModel
import com.team23.view.LocalTitle
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.topScreenHeight
import com.team23.view.mapper.RecipeUiMapper
import com.team23.view.navigation.AppNavigator
import com.team23.view.search_textfield_label
import com.team23.view.search_textfield_placeholder
import com.team23.view.widget.search.SearchTagsRow
import com.team23.view.widget.search.SearchTextField
import kotlinx.coroutines.flow.collectLatest
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
        val recipeUiMapper = remember { RecipeUiMapper() }

        LaunchedEffect(true) {
            searchViewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is SearchUiEvent.NavigateToRecipe -> appNavigator.navigateToRecipe(navigator, event.recipeId)
                }
            }
        }

        LocalTitle.current.value = null
        searchViewModel.selectedTag = selectedTag?.let { TagUiModel.Label(it) }

        SearchScreen(
            searchUiModel = SearchUiModel(
                textField = TextFieldUiModel(
                    searchValue = searchViewModel.searchValue.value,
                    label = stringResource(Res.string.search_textfield_label),
                    placeholder = stringResource(Res.string.search_textfield_placeholder),
                    leadingIcon = IconProperty.Vector(
                        imageVector = Icons.Filled.Search,
                        tint = ColorProperty.AccentIcon,
                    ),
                ),
                tagsRow = TagsRowUiModel(
                    tags = searchViewModel.tags.collectAsState().value,
                ),
                items = searchViewModel.recipes.collectAsState().value.map { recipe ->
                    SearchUiModel.Item(
                        id = recipe.id,
                        cellProperty = recipeUiMapper.toCellProperty(
                            recipe = recipe,
                            displayType = DisplayType.List,
                        ),
                    )
                },
            ),
            performAction = searchViewModel::handleSearchAction,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun SearchScreen(
    searchUiModel: SearchUiModel,
    modifier: Modifier = Modifier,
    performAction: (SearchAction) -> Unit = { },
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .padding(start = 16.dp, top = 8.dp, end = 16.dp)
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.topScreenHeight(additionalHeight = 24.dp))
        SearchTextField(searchUiModel.textField) { newValue ->
            performAction(SearchAction.SearchValueChange(newValue))
        }

        SearchTagsRow(searchUiModel.tagsRow) { tag ->
            performAction(SearchAction.TagClick(tag))
        }

        LazyVerticalGrid(
            columns = GridCells.Adaptive(300.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp),
            modifier = modifier.fillMaxSize()
        ) {
            items(
                items = searchUiModel.items,
                key = { item -> item.id },
            ) { item ->
                Cell(
                    cellProperty = item.cellProperty,
                    onAction = { action ->
                        when (action) {
                            CellAction.FavoriteClick -> performAction(SearchAction.FavoriteClick(item.id, item.cellProperty.title))
                            CellAction.LocalPhoneClick -> performAction(SearchAction.LocalPhoneClick)
                        }
                    },
                    modifier = Modifier
                        .animateItem()
                        .clickable {
                            performAction(SearchAction.RecipeClick(item.id))
                        }
                )
            }
        }
    }
}
