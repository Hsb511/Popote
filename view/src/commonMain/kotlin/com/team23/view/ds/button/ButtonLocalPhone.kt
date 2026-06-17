package com.team23.view.ds.button

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.view.ds.icon.PopoteIcon

@Composable
fun ButtonLocalPhone(
    localPhone: CellProperty.LocalPhone,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(
        onClick = onClick,
        modifier = modifier,
    ) {
        PopoteIcon(iconProperty = localPhone.iconProperty)
    }
}
