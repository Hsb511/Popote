package com.team23.view.ds.cell

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.view.ds.flag.PopoteFlag

@Composable
internal fun CellFlag(
    flagProperty: FlagProperty,
    modifier: Modifier = Modifier,
) {
	PopoteFlag(
		flagProperty = flagProperty,
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
