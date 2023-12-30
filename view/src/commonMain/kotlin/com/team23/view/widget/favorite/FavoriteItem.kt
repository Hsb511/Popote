package com.team23.view.widget.favorite

import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.SummarizedRecipeUiModel
import com.team23.view.ds.cell.Cell
import com.team23.view.mapper.RecipeUiMapper

@Composable
fun FavoriteItem(
    displayType: DisplayType,
    summarizedRecipe: SummarizedRecipeUiModel,
    onRecipeClick: (SummarizedRecipeUiModel) -> Unit,
    onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
    onLocalPhoneClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val recipeUiMapper = RecipeUiMapper()

    Cell(
        cellProperty = recipeUiMapper.toCellProperty(
            recipe = summarizedRecipe,
            displayType = displayType,
            onFavoriteClick = { onFavoriteClick(summarizedRecipe) },
            onLocalPhoneClick = onLocalPhoneClick,
        ),
        modifier = modifier.clickable {
            onRecipeClick(summarizedRecipe)
        }
    )
}
