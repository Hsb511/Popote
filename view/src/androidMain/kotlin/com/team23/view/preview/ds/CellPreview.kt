package com.team23.view.preview.ds

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.neuracrsrecipes.model.property.DisplayType
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.view.ds.cell.CellCard
import com.team23.view.ds.cell.CellFlag
import com.team23.view.ds.cell.CellList
import com.team23.view.ds.cell.Cell
import com.team23.view.sample.property.SampleDisplayTypeProvider
import com.team23.view.sample.property.getCellPropertySample
import com.team23.view.theme.PopoteTheme


@Composable
@Preview(showBackground = true)
private fun FlagPropertyCellPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
    PopoteTheme {
        Cell(cellProperty = getCellPropertySample(displayType))
    }
}

@Composable
@Preview(showBackground = true)
private fun CellCardPreview() {
    PopoteTheme {
        CellCard(cellProperty = getCellPropertySample(), displayType = DisplayType.BigCard)
    }
}

@Composable
@Preview(showBackground = true)
private fun CellFlagPreview() {
    PopoteTheme {
        Box {
            CellFlag(FlagProperty.FRENCH)
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun CellListPreview() {
    MaterialTheme {
        CellList(getCellPropertySample())
    }
}
