package com.team23.view.widget.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.HomeAction
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.horizontalGutterPadding
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
                Spacer(modifier = Modifier.height(48.dp))
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                Text(
                    text = stringResource(Res.string.home_title),
                    style = MaterialTheme.typography.displaySmall,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
            items(homeData.recipes) { summarizedRecipeUiModel ->
                Cell(
                    cellProperty = RecipeUiMapper().toCellProperty(
                        recipe = summarizedRecipeUiModel,
                        displayType = DisplayType.BigCard,
                        onFavoriteClick = {
                            onAction(
                                HomeAction.ToggleFavorite(
                                    summarizedRecipeUiModel
                                )
                            )
                        },
                        onLocalPhoneClick = { onAction(HomeAction.ShowLocalPhoneMessage) },
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            homeRecipeClick(summarizedRecipeUiModel.id)
                        }
                )
            }
        }
    }
}
