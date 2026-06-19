package com.team23.view.ds.cell

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.FlagProperty
import com.team23.view.Res
import com.team23.view.ds.flag.PopoteFlag
import com.team23.view.ic_language
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun CellFlag(
	flagProperty: FlagProperty,
	modifier: Modifier = Modifier,
	withLanguageWatermark: Boolean = false,
	topStartCorner: Dp? = 0.dp,
	topEndCorner: Dp? = null,
	bottomStartCorner: Dp? = null,
	bottomEndCorner: Dp? = 0.dp,
) {
	Box(modifier = modifier) {
		PopoteFlag(
			flagProperty = flagProperty,
			modifier = Modifier
				.clip(
					shape = MaterialTheme.shapes.medium.copy(
						topStart = topStartCorner?.let { CornerSize(it) } ?: MaterialTheme.shapes.medium.topStart,
						topEnd = topEndCorner?.let { CornerSize(it) } ?: MaterialTheme.shapes.medium.topEnd,
						bottomStart = bottomStartCorner?.let { CornerSize(it) } ?: MaterialTheme.shapes.medium.bottomStart,
						bottomEnd = bottomEndCorner?.let { CornerSize(it) } ?: MaterialTheme.shapes.medium.bottomEnd,
					)
				)
				.width(30.dp)
				.height(20.dp)
		)
		if (withLanguageWatermark) {
			Icon(
				painter = painterResource(Res.drawable.ic_language),
				contentDescription = null,
				tint = Black.copy(alpha = 0.5f),
				modifier = Modifier
					.size(20.dp)
					.align(Alignment.Center)
			)
		}
	}
}
