package com.team23.design_system.like

import androidx.compose.foundation.layout.offset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.R
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun NeuracrLike(
	isFavorite: Boolean,
	onFavoriteClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	IconButton(
		onClick = onFavoriteClick,
		modifier = modifier.offset(x = 8.dp, y = 8.dp),
	) {
		Icon(
			imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
			contentDescription = stringResource(id = R.string.favorite_button_content_description),
			tint = if (isFavorite) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onBackground,
		)
	}
}

@Composable
@Preview(showBackground = true)
private fun NeuracrLikePreview() {
	NeuracrTheme {
		NeuracrLike(true, {})
	}
}
