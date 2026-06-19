package com.team23.view.widget.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import com.team23.neuracrsrecipes.model.uimodel.PromotedLaneUiModel
import com.team23.neuracrsrecipes.model.uistate.HomeUiState
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.getCurrentScreenHeight
import com.team23.view.extension.getCurrentScreenWidth
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.extension.topScreenHeight
import com.team23.view.home_seasonal_section_title
import com.team23.view.home_title
import com.team23.view.home_vegan_section_title
import com.team23.view.home_vegetarian_section_title
import com.team23.view.mapper.RecipeUiMapper
import org.jetbrains.compose.resources.StringResource
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
        val recipeCellProperties = remember(homeData.recipes) {
            homeData.recipes.map { recipe ->
                recipeUiMapper.toCellProperty(
                    recipe = recipe,
                    displayType = DisplayType.BigCard,
                )
            }
        }
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
                val recipeCellProperties = remember(homeData.recipes) {
                    promotedLane.recipes.map { recipe ->
                        recipeUiMapper.toCellProperty(
                            recipe = recipe,
                            displayType = DisplayType.BigCard,
                            maxHeight = 200.dp,
                        )
                    }
                }
                HomePromotedLane(
                    title = stringResource(getTitleRes(promotedLane.type)),
                    recipeCellProperties = recipeCellProperties,
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
                items = recipeCellProperties,
                key = { _, recipe -> recipe.id },
                contentType = { _, _ -> "recipeTile" },
            ) { index, cellProperty ->
                Row(modifier = Modifier
                    .padding(
                        start = if (isLeft(index)) horizontalGutterPadding else 0.dp,
                        end = if (isRight(index)) horizontalGutterPadding else 0.dp,
                    )
                ) {
                    Cell(
                        cellProperty = cellProperty,
                        onAction = { action -> handleCellAction(action, cellProperty.id, cellProperty.title, onAction) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = getCurrentScreenHeight() / 2)
                            .clickable {
                                homeRecipeClick(cellProperty.id)
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

private fun getTitleRes(type: PromotedLaneUiModel.Type): StringResource = when(type) {
    PromotedLaneUiModel.Type.Seasonal -> Res.string.home_seasonal_section_title
    PromotedLaneUiModel.Type.Vegetarian -> Res.string.home_vegetarian_section_title
    PromotedLaneUiModel.Type.Vegan -> Res.string.home_vegan_section_title
}

private val minColumnSize = 250.dp

internal fun handleCellAction(action: CellAction, recipeId: String, recipeTitle: String, onAction: (HomeAction) -> Unit) {
    when (action) {
        CellAction.FavoriteClick -> onAction(HomeAction.ToggleFavorite(recipeId, recipeTitle))
        CellAction.LocalPhoneClick -> onAction(HomeAction.ShowLocalPhoneMessage)
    }
}
