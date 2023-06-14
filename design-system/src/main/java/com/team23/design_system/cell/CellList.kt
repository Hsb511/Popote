package com.team23.design_system.cell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.display.DisplayType
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty

@Composable
fun CellList(
	neuracrCellProperty: NeuracrCellProperty,
	modifier: Modifier = Modifier,
) {
	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
		modifier = modifier.height(64.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxSize(),
		) {
			NeuracrImage(
				neuracrImageProperty = neuracrCellProperty.imageProperty,
				maxImageHeight = 200.dp,
				hasNoCornerEnd = true,
				contentScale = ContentScale.FillBounds,
				modifier = Modifier
					.fillMaxHeight()
					.width(64.dp),
			)
			Box(modifier = Modifier.fillMaxSize()) {
				Text(
					text = neuracrCellProperty.title,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.titleSmall,
					modifier = Modifier
						.padding(all = 8.dp)
						.align(Alignment.CenterStart)
				)
				CellFlag(neuracrCellProperty.flagProperty)
				CellLike(neuracrCellProperty.isFavorite, neuracrCellProperty.onFavoriteClick)
			}
		}
	}
}

@Composable
@Preview(showBackground = true)
fun CellListPreview() {
	MaterialTheme {
		CellList(
			NeuracrCellProperty(
				displayType = DisplayType.BigCard,
				title = "bretzels",
				imageProperty = NeuracrImageProperty.Resource(
					contentDescription = null,
					imageRes = com.team23.design_system.R.drawable.bretzel
				),
				flagProperty = NeuracrFlagProperty.FRENCH,
				isFavorite = true,
				onFavoriteClick = {}
			)
		)
	}
}
