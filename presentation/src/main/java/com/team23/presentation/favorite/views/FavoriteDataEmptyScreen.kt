package com.team23.presentation.favorite.views

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R

@Composable
fun FavoriteDataEmptyScreen(modifier: Modifier = Modifier) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.fillMaxSize()
			.padding(all = 32.dp),
	) {
		Text(
			text = stringResource(id = R.string.favorite_empty_title),
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
		)
		NeuracrImage(
			neuracrImageProperty = NeuracrImageProperty.Resource(null, com.team23.design_system.R.drawable.neuracr_wip),
			maxImageHeight = 230.dp,
			modifier = Modifier.padding(vertical = 32.dp)
		)

		val description = buildAnnotatedString {
			append(stringResource(id = R.string.favorite_empty_description_part1) + " ")
			appendInlineContent(id = "heart")
			append(" " + stringResource(id = R.string.favorite_empty_description_part2))
		}
		val inlineContentMap = mapOf(
			"heart" to InlineTextContent(
				Placeholder(20.sp, 20.sp, PlaceholderVerticalAlign.TextCenter)
			) {
				Icon(
					imageVector = Icons.Outlined.FavoriteBorder,
					contentDescription = null,
					tint = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.onBackground else MaterialTheme.colorScheme.tertiary.copy(
						alpha = 0.69f
					),
					modifier = Modifier.fillMaxSize(),
				)
			}
		)
		Text(
			text = description,
			inlineContent = inlineContentMap,
			style = MaterialTheme.typography.bodyLarge,
			textAlign = TextAlign.Center,
		)
	}
}

@Composable
@Preview(showSystemUi = true)
fun FavoriteDataEmptyScreenPreview() {
	NeuracrTheme {
		FavoriteDataEmptyScreen()
	}
}
