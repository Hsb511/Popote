package com.team23.view.widget.grocery

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.action.GroceryListAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.neuracrsrecipes.model.uimodel.GroceryListUiModel
import com.team23.view.Res
import com.team23.view.ds.cell.Cell
import com.team23.view.ds.icon.PopoteIcon
import com.team23.view.extension.getCurrentScreenWidth
import com.team23.view.extension.topScreenHeight
import com.team23.view.grocery_list_clear_a11y
import com.team23.view.grocery_list_ingredients_section
import com.team23.view.grocery_list_recipes_section
import com.team23.view.grocery_list_title
import com.team23.view.ic_content_copy
import com.team23.view.mapper.RecipeUiMapper
import com.team23.view.recipe_copy_to_clipboard_a11y
import org.jetbrains.compose.resources.stringResource

@Composable
fun GroceryListData(
    uiModel: GroceryListUiModel,
    modifier: Modifier = Modifier,
    onAction: (GroceryListAction) -> Unit = {},
) {

    val recipeUiMapper = remember { RecipeUiMapper() }
    val recipes = uiModel.recipes.map { recipe -> recipeUiMapper.toCellProperty(recipe.uiModel, DisplayType.List) to recipe.servingsAmount }
    LazyColumn(modifier = modifier.padding(horizontal = 16.dp)) {
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

        verticalSpacer(8.dp)

        items(
            items = recipes,
            key = { it.first.id },
        ) { (cellProperty, servingsAmount) ->
            GroceryListRecipe(
                cellProperty = cellProperty,
                servingsAmount = servingsAmount,
                onRecipeClick = { onAction(GroceryListAction.OnRecipeClick(cellProperty.id)) },
                onChangeServingsAmount = { newAmount -> onAction(GroceryListAction.ChangeServingsAmount(cellProperty.id, newAmount)) },
                onCellAction = { cellAction -> onAction(GroceryListAction.OnCellAction(cellAction, cellProperty.id, cellProperty.title)) },
            )

        }

        verticalSpacer(8.dp)

        item {
            GroceryListIngredientsSectionTitle(onCopyClick = { onAction(GroceryListAction.CopyIngredients) })
        }

        verticalSpacer(8.dp)

        items(
            items = uiModel.ingredients,
            key = { ingredient -> ingredient.id },
        ) { ingredient ->
            GroceryListIngredient(
                ingredient = ingredient,
                onCheckedChange = { onAction(GroceryListAction.ToggleIngredient(ingredient)) }
            )
        }

        verticalSpacer(16.dp)
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
                imageVector = Icons.Outlined.Delete,
                contentDescription = stringResource(Res.string.grocery_list_clear_a11y),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun GroceryListIngredientsSectionTitle(
    onCopyClick: () -> Unit = {},
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(Res.string.grocery_list_ingredients_section),
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = { onCopyClick() },
        ) {
            PopoteIcon(
                iconProperty = IconProperty.Resource(
                    drawableResource = Res.drawable.ic_content_copy,
                    contentDescription = Res.string.recipe_copy_to_clipboard_a11y,
                    tint = ColorProperty.AccentIcon,
                ),
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
private fun GroceryListRecipe(
    cellProperty: CellProperty,
    servingsAmount: Int,
    modifier: Modifier = Modifier,
    onRecipeClick: () -> Unit = {},
    onChangeServingsAmount: (Int) -> Unit = {},
    onCellAction: (CellAction) -> Unit = {}
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.padding(vertical = 4.dp),
    ) {
        Cell(
            cellProperty = cellProperty,
            onAction = onCellAction,
            modifier = Modifier
                .width(getCurrentScreenWidth() - 16.dp * 2 - 8.dp - 88.dp)
                .clickable { onRecipeClick() }
        )
        GroceryListServingsWidget(
            currentServingsAmount = servingsAmount.toString(),
            onAddOneServing = { onChangeServingsAmount(servingsAmount + 1) },
            onSubtractOneServing = { if (servingsAmount > 1) onChangeServingsAmount(servingsAmount - 1) },
        )
    }
}

@Composable
private fun GroceryListServingsWidget(
    currentServingsAmount: String,
    onAddOneServing: () -> Unit = {},
    onSubtractOneServing: () -> Unit = {},
) {
    Box(contentAlignment = Alignment.Center) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .background(
                    color = MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.extraLarge
                )
        ) {
            IconButton(
                onClick = onSubtractOneServing,
                modifier = Modifier.size(36.dp)
            ) {
                Text(
                    text = "−",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = onAddOneServing,
                modifier = Modifier.size(36.dp)
            ) {
                Text(
                    text = "+",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSecondary,
                )
            }
        }
        Text(
            text = currentServingsAmount,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSecondary,
        )
    }
}

@Composable
private fun GroceryListIngredient(
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
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(64.dp),
        )
    }
}

fun LazyListScope.verticalSpacer(height: Dp) {
    item {
        Spacer(modifier = Modifier.height(height))
    }
}
