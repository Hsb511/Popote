package com.team23.design_system.cell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.like.NeuracrLike

@Composable
internal fun BoxScope.CellLike(isFavorite: Boolean, onFavoriteClick: () -> Unit, ) {
	NeuracrLike(
		isFavorite = isFavorite,
		onFavoriteClick = onFavoriteClick,
		modifier = Modifier.align(Alignment.BottomEnd),
	)
}

@Composable
@Preview(showBackground = true)
internal fun CellLikePreview() {
    MaterialTheme {
	    Box {
		    CellLike(true) {}
	    }
    }
}
