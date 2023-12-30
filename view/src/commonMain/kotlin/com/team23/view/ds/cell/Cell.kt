package com.team23.view.ds.cell

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.CellProperty

@Composable
fun Cell(
    cellProperty: CellProperty,
    modifier: Modifier = Modifier,
) {
    when (cellProperty.displayType) {
        DisplayType.BigCard,
        DisplayType.SmallCard -> CellCard(cellProperty, modifier)

        DisplayType.List -> CellList(cellProperty, modifier)
    }
}
