package com.team23.view.ds.button

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.ds.icon.PopoteIcon

@Composable
fun ButtonGroceryList(
    iconProperty: IconProperty.Resource,
    modifier: Modifier = Modifier,
    onGroceryListClick: () -> Unit = {},
) {

    IconButton(
        onClick = onGroceryListClick,
        modifier = modifier,
    ) {

        Crossfade(targetState = iconProperty.tint, animationSpec = tween(500)) { color ->
            PopoteIcon(iconProperty = iconProperty.copy(tint = color))
        }
    }
}
