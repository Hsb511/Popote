package com.team23.view.widget.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.action.HomeAction
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.extension.topScreenHeight
import com.team23.view.home_title
import com.team23.view.mapper.RecipeUiMapper
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContentData(
    homeData: HomeUiState.Data,
    homeRecipeClick: (String) -> Unit,
    onAction: (HomeAction) -> Unit,
) {
    val refreshState = rememberPullToRefreshState()
    val recipeUiMapper = remember { RecipeUiMapper() }

    PullToRefreshBox(
        isRefreshing = homeData.isRefreshing,
        state = refreshState,
        onRefresh = { onAction(HomeAction.RefreshRecipes) },
        indicator = {
            Indicator(
                isRefreshing = homeData.isRefreshing,
                state = refreshState,
                modifier = Modifier
                    .padding(top = 56.dp)
                    .align(Alignment.TopCenter),
            )
        }
    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(300.dp),
            contentPadding = PaddingValues(horizontalGutterPadding),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Spacer(modifier = Modifier.topScreenHeight())
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                Text(
                    text = stringResource(Res.string.home_title),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            items(
                items = homeData.recipes,
                key = { recipe -> recipe.id }
            ) { recipe ->
                Cell(
                    cellProperty = recipeUiMapper.toCellProperty(
                        recipe = recipe,
                        displayType = DisplayType.BigCard,
                    ),
                    onAction = { action ->
                        when (action) {
                            CellAction.FavoriteClick -> onAction(HomeAction.ToggleFavorite(recipe))
                            CellAction.LocalPhoneClick -> onAction(HomeAction.ShowLocalPhoneMessage)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            homeRecipeClick(recipe.id)
                        }
                )
            }
        }
    }
}
