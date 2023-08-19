package com.team23.design_system.cell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.flags.NeuracrFlag
import com.team23.design_system.flags.NeuracrFlagProperty

@Composable
internal fun CellFlag(
	flagProperty: NeuracrFlagProperty,
	modifier: Modifier = Modifier,
) {
	NeuracrFlag(
		neuracrFlagProperty = flagProperty,
		modifier = modifier
			.clip(
				shape = MaterialTheme.shapes.medium.copy(
					topStart = CornerSize(0.dp),
					bottomEnd = CornerSize(0.dp)
				)
			)
			.width(30.dp)
			.height(20.dp)
	)
}

@Composable
@Preview(showBackground = true)
internal fun CellFlagPreview() {
	MaterialTheme {
		Box {
			CellFlag(NeuracrFlagProperty.FRENCH)
		}
	}
}
