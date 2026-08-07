package com.team23.view.widget.grocery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.WeeklyGroceryListAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.WeeklyGroceryListUiModel
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.extension.topScreenHeight
import com.team23.view.mapper.RecipeUiMapper
import com.team23.view.weekly_grocery_list_clear_a11y
import com.team23.view.weekly_grocery_list_ingredients_section
import com.team23.view.weekly_grocery_list_recipes_section
import com.team23.view.weekly_grocery_list_title
import com.team23.view.widget.recipe.RecipeServingsWidget
import org.jetbrains.compose.resources.stringResource

@Composable
fun WeeklyGroceryListData(
    uiModel: WeeklyGroceryListUiModel,
    modifier: Modifier = Modifier,
    onAction: (WeeklyGroceryListAction) -> Unit = {},
) {

    val recipeUiMapper = remember { RecipeUiMapper() }
    val recipes = uiModel.recipes.map { recipe -> recipeUiMapper.toCellProperty(recipe.uiModel, DisplayType.List) to recipe.servingsAmount }
    LazyColumn(modifier = modifier.padding(horizontal = horizontalGutterPadding)) {
        item {
            Spacer(modifier = Modifier.topScreenHeight(48.dp))
        }

        item {
            Row {
                Text(
                    text = stringResource(Res.string.weekly_grocery_list_title),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { onAction(WeeklyGroceryListAction.Clear) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(Res.string.weekly_grocery_list_clear_a11y),
                    )
                }
            }

        }

        item {
            Text(
                text = stringResource(Res.string.weekly_grocery_list_recipes_section),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        items(
            items = recipes,
            key = { it.first.id },
        ) { (cellProperty, servingsAmount) ->
            WeeklyGroceryListRecipe(
                cellProperty = cellProperty,
                servingsAmount = servingsAmount,
                onRecipeClick = { onAction(WeeklyGroceryListAction.OnRecipeClick(cellProperty.id)) },
            )

        }

        item {
            Text(
                text = stringResource(Res.string.weekly_grocery_list_ingredients_section),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        items(
            items = uiModel.ingredients,
            key = { ingredient -> ingredient.id },
        ) { ingredient ->
            WeeklyGroceryListIngredient(
                ingredient = ingredient,
                onCheckedChange = { onAction(WeeklyGroceryListAction.ToggleIngredient(ingredient)) }
            )
        }
    }
}


@Composable
private fun WeeklyGroceryListRecipe(
    cellProperty: CellProperty,
    servingsAmount: Int,
    modifier: Modifier = Modifier,
    onRecipeClick: () -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Cell(
            cellProperty = cellProperty,
            modifier = Modifier
                .clickable { onRecipeClick() }
        )
        RecipeServingsWidget(
            currentServingsAmount = servingsAmount.toString(),
        )
    }
}


@Composable
private fun WeeklyGroceryListIngredient(
    ingredient: WeeklyGroceryListUiModel.Ingredient,
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Checkbox(
            checked = ingredient.isChecked,
            onCheckedChange = onCheckedChange,
        )
        Text(
            text = ingredient.uiModel.label,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f)
        )
        Text(
            text = ingredient.uiModel.quantity.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(64.dp)
        )
        Text(
            text = ingredient.uiModel.unit.orEmpty(),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(32.dp)
        )
    }
}
