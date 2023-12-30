package com.team23.view.ds.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.CellProperty
import com.team23.neuracrsrecipes.model.property.IconProperty
import com.team23.view.ds.button.ButtonLike
import com.team23.view.ds.button.ButtonLocalPhone
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.extension.stringResource

@Composable
internal fun CellCard(
	cellProperty: CellProperty,
	modifier: Modifier = Modifier,
) {
	Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
		Box(modifier = modifier.clip(shape = MaterialTheme.shapes.medium)) {
			NeuracrImage(
				neuracrImageProperty = cellProperty.imageProperty,
				// TODO EXPECT / ACTUAL ON EACH PLATFORM
				maxImageHeight = (420.dp - 64.dp) * 3 / 4,
				modifier = Modifier.fillMaxWidth()
			)
			if (cellProperty.isLocallySaved) {
				ButtonLocalPhone(
					localPhone = CellProperty.LocalPhone(
						iconProperty = 	IconProperty.Resource(
							fileName = "drawable/ic_local_smartphone.xml",
							contentDescription = stringResource(id = "locally_saved_button_content_description"),
							tint = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.69f),
						),
						onLocalPhoneClick = cellProperty.localPhone.onLocalPhoneClick,
					),
					modifier = Modifier.align(Alignment.TopStart).offset(x = (-8).dp, y = (-8).dp),
				)
			}
			Text(
				text = cellProperty.title,
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
			CellFlag(cellProperty.flagProperty, Modifier.align(Alignment.TopEnd))
			ButtonLike(
				iconProperty = cellProperty.favorite.iconProperty,
				onFavoriteClick = cellProperty.favorite.onFavoriteClick,
				modifier = Modifier.align(Alignment.BottomEnd).offset(x = 8.dp, y = 8.dp)
			)
		}
	}
}
