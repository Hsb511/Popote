package com.team23.view.ds.cell

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.view.ds.cell.CellCard
import com.team23.view.ds.cell.CellList

@Composable
fun NeuracrCell(
    neuracrCellProperty: CellProperty,
    modifier: Modifier = Modifier,
) {
    when (neuracrCellProperty.displayType) {
        DisplayType.BigCard,
        DisplayType.SmallCard -> CellCard(neuracrCellProperty, modifier)

        DisplayType.List -> CellList(neuracrCellProperty, modifier)
    }
}
