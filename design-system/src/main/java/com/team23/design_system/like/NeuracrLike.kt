package com.team23.design_system.like

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.R
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun NeuracrLike(
	isFavorite: Boolean,
	onFavoriteClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	val initialColor = computeColor(isFavorite)
	var currentColor: Color by remember { mutableStateOf(initialColor) }
	currentColor = computeColor(isFavorite)

	IconButton(
		onClick = { onFavoriteClick() },
		modifier = modifier.offset(x = 8.dp, y = 8.dp),
	) {

		Crossfade(targetState = currentColor, animationSpec = tween(500)) { color ->
			Icon(
				imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
				contentDescription = stringResource(id = R.string.favorite_button_content_description),
				tint = color,
			)
		}
	}
}

@Composable
private fun computeColor(isFavorite: Boolean) =
	if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f)

@Composable
@Preview(showBackground = true)
private fun NeuracrLikePreview() {
	NeuracrTheme {
		NeuracrLike(true, {})
	}
}
