package com.team23.view.widget.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.getCurrentScreenWidth
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
            columns = StaggeredGridCells.Adaptive(minColumnSize),
            verticalItemSpacing = 16.dp,
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item(span = StaggeredGridItemSpan.FullLine) {
                Spacer(modifier = Modifier.topScreenHeight(32.dp))
            }
            items(
                items = homeData.promotedLanes,
                key = { lane -> lane.type },
                span = { StaggeredGridItemSpan.FullLine },
                contentType = { "promotedLane" },
            ) { promotedLane ->
                HomePromotedLane(
                    promotedLane = promotedLane,
                    recipeUiMapper = recipeUiMapper,
                    onAction = onAction,
                    homeRecipeClick = homeRecipeClick,
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                HomeSectionTitle(
                    text = stringResource(Res.string.home_title),
                    modifier = Modifier
                        .padding(horizontal = horizontalGutterPadding),
                )
            }
            itemsIndexed(
                items = homeData.recipes,
                key = { _, recipe -> recipe.id },
                contentType = { _, _ -> "recipeTile" },
            ) { index, recipe ->
                Row(modifier = Modifier
                    .padding(
                        start = if (isLeft(index)) horizontalGutterPadding else 0.dp,
                        end = if (isRight(index)) horizontalGutterPadding else 0.dp,
                    )
                ) {
                    Cell(
                        cellProperty = recipeUiMapper.toCellProperty(
                            recipe = recipe,
                            displayType = DisplayType.BigCard,
                        ),
                        onAction = { action -> handleCellAction(action, recipe, onAction) },
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
}

@Composable
private fun isLeft(index: Int): Boolean {
    val columns = getAdaptiveColumns()
    return index % columns == 0
}

@Composable
private fun isRight(index: Int): Boolean {
    val columns = getAdaptiveColumns()
    return index % columns == columns - 1
}

@Composable
private fun getAdaptiveColumns(): Int {
    val screenWidthDp = getCurrentScreenWidth().value
    val minSize = minColumnSize.value
    val horizontalSpacing = 16.dp.value

    return ((screenWidthDp - horizontalSpacing) / (minSize + horizontalSpacing)).toInt().coerceAtLeast(1)
}

private val minColumnSize = 250.dp

internal fun handleCellAction(action: CellAction, recipe: SummarizedRecipeUiModel, onAction: (HomeAction) -> Unit) {
    when (action) {
        CellAction.FavoriteClick -> onAction(HomeAction.ToggleFavorite(recipe))
        CellAction.LocalPhoneClick -> onAction(HomeAction.ShowLocalPhoneMessage)
    }
}
