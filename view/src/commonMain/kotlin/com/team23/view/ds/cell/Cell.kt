package com.team23.view.ds.cell

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.action.CellAction
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.DisplayType

@Composable
fun Cell(
    cellProperty: CellProperty,
    modifier: Modifier = Modifier,
    onAction: (CellAction) -> Unit = {},
) {
    when (cellProperty.displayType) {
        DisplayType.BigCard -> CellCard(
            cellProperty = cellProperty,
            displayType = cellProperty.displayType,
            onAction = onAction,
            modifier = modifier
        )
        DisplayType.SmallCard -> CellCard(
            cellProperty = cellProperty,
            displayType = cellProperty.displayType,
            onAction = onAction,
            modifier = modifier
        )
        DisplayType.List -> CellList(
            neuracrCellProperty = cellProperty,
            onAction = onAction,
            modifier = modifier,
        )
    }
}
