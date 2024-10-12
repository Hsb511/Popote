package com.team23.view.ds.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.extension.getImageBitmapFromUri
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.ds.shimmer.Shimmer
import io.kamel.core.ExperimentalKamelApi
import io.kamel.image.KamelImage
import io.kamel.image.KamelImageBox
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalKamelApi::class)
@Composable
fun NeuracrImage(
	neuracrImageProperty: ImageProperty,
	maxImageHeight: Dp,
	modifier: Modifier = Modifier,
	contentScale: ContentScale = ContentScale.FillWidth,
	hasNoCornerEnd: Boolean = false,
) {
	val imageModifier = modifier
		.clip(
			shape = if (hasNoCornerEnd) MaterialTheme.shapes.medium.copy(
				topEnd = CornerSize(0.dp),
				bottomEnd = CornerSize(0.dp),
			) else MaterialTheme.shapes.medium
		)
	when (neuracrImageProperty) {
		is ImageProperty.Resource -> Image(
			painter = painterResource(neuracrImageProperty.imageRes),
			contentDescription = neuracrImageProperty.contentDescription,
			contentScale = contentScale,
			modifier = imageModifier.heightIn(max = maxImageHeight),
		)
		is ImageProperty.Remote -> {
			var dynamicMaxImageHeight = maxImageHeight

			KamelImageBox(
				resource = asyncPainterResource(neuracrImageProperty.url),
				onLoading = {
					dynamicMaxImageHeight = 1.dp
					Shimmer(modifier.height(maxImageHeight))
				},
				onSuccess = { painter ->
					val imageRatio = painter.intrinsicSize.width / painter.intrinsicSize.height
					val remoteContentScale = when {
						contentScale == ContentScale.FillBounds -> ContentScale.FillBounds
						imageRatio > 9f / 16 -> ContentScale.FillWidth
						else -> ContentScale.FillHeight
					}
					Image(
						painter = painter,
						contentDescription = neuracrImageProperty.contentDescription,
						contentScale = remoteContentScale,
					)
				},
				modifier = imageModifier.heightIn(max = dynamicMaxImageHeight),
			)
		}
		is ImageProperty.UserPick -> Image(
			bitmap = getImageBitmapFromUri(neuracrImageProperty.uri),
			contentDescription = neuracrImageProperty.contentDescription,
			contentScale = contentScale,
			modifier = imageModifier.heightIn(max = maxImageHeight),
		)
		is ImageProperty.None -> Unit
	}
}
