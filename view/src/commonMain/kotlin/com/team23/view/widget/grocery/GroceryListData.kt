package com.team23.view.widget.grocery

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.team23.neuracrsrecipes.model.action.GroceryListAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.uimodel.GroceryListUiModel
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.extension.horizontalGutterPadding
import com.team23.view.extension.topScreenHeight
import com.team23.view.mapper.RecipeUiMapper
import com.team23.view.grocery_list_clear_a11y
import com.team23.view.grocery_list_ingredients_section
import com.team23.view.grocery_list_recipes_section
import com.team23.view.grocery_list_title
import com.team23.view.widget.recipe.RecipeServingsWidget
import org.jetbrains.compose.resources.stringResource

@Composable
fun GroceryListData(
    uiModel: GroceryListUiModel,
    modifier: Modifier = Modifier,
    onAction: (GroceryListAction) -> Unit = {},
) {

    val recipeUiMapper = remember { RecipeUiMapper() }
    val recipes = uiModel.recipes.map { recipe -> recipeUiMapper.toCellProperty(recipe.uiModel, DisplayType.List) to recipe.servingsAmount }
    LazyColumn(modifier = modifier.padding(horizontal = horizontalGutterPadding)) {
        item {
            Spacer(modifier = Modifier.topScreenHeight(48.dp))
        }

        item {
            GroceryListHeader(clearList = { onAction(GroceryListAction.Clear) })
        }

        item {
            Text(
                text = stringResource(Res.string.grocery_list_recipes_section),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        items(
            items = recipes,
            key = { it.first.id },
        ) { (cellProperty, servingsAmount) ->
            GroceryListRecipe(
                cellProperty = cellProperty,
                servingsAmount = servingsAmount,
                onRecipeClick = { onAction(GroceryListAction.OnRecipeClick(cellProperty.id)) },
            )

        }

        item {
            Text(
                text = stringResource(Res.string.grocery_list_ingredients_section),
                style = MaterialTheme.typography.headlineSmall,
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        items(
            items = uiModel.ingredients,
            key = { ingredient -> ingredient.id },
        ) { ingredient ->
            WeeklyGroceryListIngredient(
                ingredient = ingredient,
                onCheckedChange = { onAction(GroceryListAction.ToggleIngredient(ingredient)) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun GroceryListHeader(
    clearList: () -> Unit = {},
) {
    Row {
        Text(
            text = stringResource(Res.string.grocery_list_title),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = { clearList()}) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(Res.string.grocery_list_clear_a11y),
            )
        }
    }
}

@Composable
private fun GroceryListRecipe(
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
    ingredient: GroceryListUiModel.Ingredient,
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
            modifier = Modifier.height(24.dp),
        )
        Text(
            text = ingredient.displayMainLabel,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = ingredient.displaySecondaryLabel,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.width(64.dp),
        )
    }
}
