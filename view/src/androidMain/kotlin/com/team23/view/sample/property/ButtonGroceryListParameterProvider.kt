package com.team23.view.sample.property

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.team23.neuracrsrecipes.model.property.ColorProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.Res
import com.team23.view.ic_grocery_list_add
import com.team23.view.ic_grocery_list_added

internal class ButtonGroceryListParameterProvider : PreviewParameterProvider<IconProperty.Resource> {
    override val values = sequenceOf(
        groceryListButtonPreviewSample,
        groceryListCheckedButtonPreviewSample,
    )
}

internal val groceryListButtonPreviewSample = IconProperty.Resource(
    drawableResource = Res.drawable.ic_grocery_list_add,
    tint = ColorProperty.DefaultIcon,
)

internal val groceryListCheckedButtonPreviewSample = IconProperty.Resource(
    drawableResource = Res.drawable.ic_grocery_list_added,
    tint = ColorProperty.AccentIcon,
)
