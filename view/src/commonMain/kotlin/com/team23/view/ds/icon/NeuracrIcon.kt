package com.team23.view.ds.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.IconProperty
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun NeuracrIcon(
    iconProperty: IconProperty,
    modifier: Modifier = Modifier,
) {
    when (iconProperty) {
        is IconProperty.Resource -> Icon(
            painter = painterResource(iconProperty.drawableResource),
            contentDescription = iconProperty.contentDescription,
            tint = iconProperty.tint,
            modifier = modifier,
        )

        is IconProperty.Vector -> Icon(
            imageVector = iconProperty.imageVector,
            contentDescription = iconProperty.contentDescription,
            tint = iconProperty.tint,
            modifier = modifier,
        )
    }
}
