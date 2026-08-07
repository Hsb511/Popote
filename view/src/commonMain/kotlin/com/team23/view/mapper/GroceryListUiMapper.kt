package com.team23.view.mapper

import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.ic_grocery_list_add
import com.team23.view.ic_grocery_list_added
import org.jetbrains.compose.resources.DrawableResource

class GroceryListUiMapper {

    fun toGroceryListIconProperty(isInGroceryList: Boolean): IconProperty.Resource = IconProperty.Resource(
        drawableResource = groceryListImageVector(isInGroceryList),
        tint = groceryListTint(isInGroceryList),
    )

    private fun groceryListImageVector(isInGroceryList: Boolean): DrawableResource =
        if (isInGroceryList) Res.drawable.ic_grocery_list_added else Res.drawable.ic_grocery_list_add

    private fun groceryListTint(isInGroceryList: Boolean): ColorProperty =
        if (isInGroceryList) ColorProperty.AccentIcon else ColorProperty.DefaultIcon
}
