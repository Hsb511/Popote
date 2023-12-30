package com.team23.view.ds.cell

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.CellProperty

@Composable
fun Cell(
    CellProperty: CellProperty,
    modifier: Modifier = Modifier,
) {
    when (CellProperty.displayType) {
        DisplayType.BigCard,
        DisplayType.SmallCard -> CellCard(CellProperty, modifier)

        DisplayType.List -> CellList(CellProperty, modifier)
    }
}
