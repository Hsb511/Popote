package com.team23.view.widget.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.HomeAction
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.PromotedLaneUiModel
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.home_seasonal_section_title
import com.team23.view.home_vegan_section_title
import com.team23.view.home_vegetarian_section_title
import com.team23.view.mapper.RecipeUiMapper
import org.jetbrains.compose.resources.stringResource

@Composable
fun HomePromotedLane(
    promotedLane: PromotedLaneUiModel,
    recipeUiMapper: RecipeUiMapper,
    modifier: Modifier = Modifier,
    onAction: (HomeAction) -> Unit = {},
    homeRecipeClick: (String) -> Unit = {},
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        val titleRes = when(promotedLane.type) {
            PromotedLaneUiModel.Type.Seasonal -> Res.string.home_seasonal_section_title
            PromotedLaneUiModel.Type.Vegetarian -> Res.string.home_vegetarian_section_title
            PromotedLaneUiModel.Type.Vegan -> Res.string.home_vegan_section_title
        }
        HomeSectionTitle(
            text = stringResource(titleRes),
            modifier = Modifier
                .padding(horizontal = horizontalGutterPadding),
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = horizontalGutterPadding),
        ) {
            items(
                items = promotedLane.recipes,
                key = { recipe -> recipe.id }
            ) { recipe ->
                Cell(
                    cellProperty = recipeUiMapper.toCellProperty(recipe, DisplayType.SmallCard, 200.dp),
                    onAction = { action -> handleCellAction(action, recipe, onAction) },
                    modifier = Modifier
                        .widthIn(max = 300.dp)
                        .height(200.dp)
                        .clickable { homeRecipeClick(recipe.id) },
                )
            }
        }
    }
}
