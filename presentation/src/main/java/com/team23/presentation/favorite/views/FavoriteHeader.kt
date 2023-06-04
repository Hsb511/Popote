package com.team23.presentation.favorite.views

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team23.design_system.display.DisplayBigCard
import com.team23.design_system.display.DisplayList
import com.team23.design_system.display.DisplaySmallCard
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.common.extensions.next
import com.team23.presentation.common.samples.RecipeSamples.SampleDisplayTypeProvider
import com.team23.presentation.favorite.models.DisplayType

@Composable
fun FavoriteHeader(
	displayType: DisplayType,
	onDisplayClick: () -> Unit,
	modifier: Modifier = Modifier
) {
	val animation = tween<Dp>(durationMillis = 600, easing = FastOutSlowInEasing)
	val headerPadding: Dp by animateDpAsState(if (displayType == DisplayType.BigCard) 0.dp else 16.dp, animation)

	Row(
		modifier = modifier
			.fillMaxSize()
			.padding(top = headerPadding, start = headerPadding, end = headerPadding)
	) {
		Text(
			text = stringResource(id = R.string.favorite_title),
			style = MaterialTheme.typography.displaySmall,
			color = MaterialTheme.colorScheme.onBackground,
		)
		Spacer(modifier = Modifier.weight(1f))
		IconButton(
			onClick = onDisplayClick,
			modifier = Modifier.offset(x = 8.dp, y = (-4).dp)
		) {
			val tint = MaterialTheme.colorScheme.onBackground
			val iconModifier = Modifier.padding(all = 8.dp)
			Crossfade(targetState = displayType.next(), animationSpec = tween(500)) { displayType ->
				when (displayType) {
					DisplayType.BigCard -> DisplayBigCard(tint = tint, modifier = iconModifier)
					DisplayType.SmallCard -> DisplaySmallCard(tint = tint, modifier = iconModifier)
					DisplayType.List -> DisplayList(tint = tint, modifier = iconModifier)
				}
			}

		}
	}
}

@Composable
@Preview(showBackground = true)
fun FavoriteHeaderPreview(@PreviewParameter(SampleDisplayTypeProvider::class) displayType: DisplayType) {
	NeuracrTheme {
		FavoriteHeader(
			displayType = displayType,
			onDisplayClick = { },
		)
	}
}
