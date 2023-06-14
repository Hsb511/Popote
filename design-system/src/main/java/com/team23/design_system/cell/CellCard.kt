package com.team23.design_system.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.display.DisplayType
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty

@Composable
internal fun CellCard(
	neuracrCellProperty: NeuracrCellProperty,
	modifier: Modifier = Modifier,
) {
	Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
		Box(modifier = modifier.clip(shape = MaterialTheme.shapes.medium)) {
			NeuracrImage(
				neuracrImageProperty = neuracrCellProperty.imageProperty,
				maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
				modifier = Modifier.fillMaxWidth()
			)
			Text(
				text = neuracrCellProperty.title,
				color = MaterialTheme.colorScheme.onTertiary,
				style = MaterialTheme.typography.titleSmall,
				modifier = Modifier
					.align(Alignment.BottomStart)
					.clip(
						shape = MaterialTheme.shapes.medium.copy(
							topStart = CornerSize(0.dp),
							bottomEnd = CornerSize(0.dp)
						)
					)
					.background(color = MaterialTheme.colorScheme.tertiary)
					.padding(start = 12.dp, bottom = 2.dp, end = 12.dp)
			)
			CellFlag(neuracrCellProperty.flagProperty)
			CellLike(neuracrCellProperty.isFavorite, neuracrCellProperty.onFavoriteClick)
		}
	}
}

@Composable
@Preview(showBackground = true)
internal fun CellCardPreview() {
    MaterialTheme {
	    CellCard(
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
