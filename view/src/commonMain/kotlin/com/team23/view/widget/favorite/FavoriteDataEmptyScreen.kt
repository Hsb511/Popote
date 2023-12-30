package com.team23.view.widget.favorite

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
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.extension.stringResource

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
			text = stringResource(id = "favorite_empty_title"),
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
		)
		NeuracrImage(
			neuracrImageProperty = ImageProperty.Resource(null, "drawable/neuracr_wip.png"),
			maxImageHeight = 230.dp,
			modifier = Modifier.padding(vertical = 32.dp)
		)

		val description = buildAnnotatedString {
			append(stringResource(id = "favorite_empty_description_part1") + " ")
			appendInlineContent(id = "heart")
			append(" " + stringResource(id = "favorite_empty_description_part2"))
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
