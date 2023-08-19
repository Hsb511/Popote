package com.team23.design_system.cell

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.like.NeuracrLike

@Composable
internal fun CellLike(
	isFavorite: Boolean,
	onFavoriteClick: () -> Unit,
	modifier: Modifier = Modifier,
) {
	NeuracrLike(
		isFavorite = isFavorite,
		onFavoriteClick = onFavoriteClick,
		modifier = modifier,
	)
}

@Composable
@Preview(showBackground = true)
internal fun CellLikePreview() {
	MaterialTheme {
		Box {
			CellLike(true, {})
		}
	}
}
