package com.team23.view.ds.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.ds.shimmer.NeuracrShimmer
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
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

			KamelImage(
				resource = asyncPainterResource(neuracrImageProperty.url),
				contentDescription = neuracrImageProperty.contentDescription,
				contentScale = contentScale,
				onLoading = {
					dynamicMaxImageHeight = 1.dp
					NeuracrShimmer(modifier.height(maxImageHeight))
				},
				modifier = imageModifier.heightIn(max = dynamicMaxImageHeight),
			)
		}
		is ImageProperty.UserPick -> KamelImage(
				resource = asyncPainterResource(neuracrImageProperty.imageLocalUri),
				contentDescription = neuracrImageProperty.contentDescription,
				contentScale = contentScale,
				modifier = imageModifier.heightIn(max = maxImageHeight),
			)
		is ImageProperty.None -> Unit
	}
}
