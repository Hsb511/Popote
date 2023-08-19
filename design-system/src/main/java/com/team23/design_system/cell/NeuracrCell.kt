package com.team23.design_system.cell

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.team23.design_system.display.DisplayType
import com.team23.design_system.display.SampleDisplayTypeProvider

@Composable
fun NeuracrCell(
	neuracrCellProperty: NeuracrCellProperty,
	modifier: Modifier = Modifier,
) {
	when (neuracrCellProperty.displayType) {
		DisplayType.BigCard,
		DisplayType.SmallCard -> CellCard(neuracrCellProperty, modifier)

		DisplayType.List -> CellList(neuracrCellProperty, modifier)
	}
}

@Composable
@Preview(showBackground = true)
fun NeuracrFlagPropertyCellPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
	MaterialTheme {
		NeuracrCell(neuracrCellProperty = getNeuracrCellPropertySample(displayType))
	}
}
