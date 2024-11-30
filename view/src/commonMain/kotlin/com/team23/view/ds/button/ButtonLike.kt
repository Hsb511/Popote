package com.team23.view.ds.button

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.ds.icon.NeuracrIcon

@Composable
fun ButtonLike(
	iconProperty: IconProperty.Vector,
	onFavoriteClick: () -> Unit,
	modifier: Modifier = Modifier,
) {

	IconButton(
		onClick = onFavoriteClick,
		modifier = modifier,
	) {

		Crossfade(targetState = iconProperty.tint, animationSpec = tween(500)) { color ->
			NeuracrIcon(iconProperty = iconProperty.copy(tint = color))
		}
	}
}
