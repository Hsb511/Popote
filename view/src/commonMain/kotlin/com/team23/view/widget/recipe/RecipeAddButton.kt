package com.team23.view.widget.recipe

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun RecipeAddButton(
	onAddClick: () -> Unit,
	widgetWidth: Int,
	modifier: Modifier = Modifier,
) {
	val density = LocalDensity.current

	Row(
		horizontalArrangement = Arrangement.Center,
		modifier = modifier.fillMaxWidth()
	) {
		Button(
			onClick = { onAddClick() },
			modifier = Modifier
				.width(with(density) { widgetWidth.toDp() })
				.height(34.dp)
		) {
			Icon(
				imageVector = Icons.Filled.Add,
				contentDescription = null,
			)
		}
	}
}
