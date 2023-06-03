package com.team23.presentation.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.flags.NeuracrFlag
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.like.NeuracrLike
import com.team23.presentation.common.samples.RecipeSamples.summarizedRecipeSample
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@Composable
internal fun HomeRecipeCard(
	summarizedRecipeUiModel: SummarizedRecipeUiModel,
	onFavoriteClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier
) {
	var isFavorite by remember { mutableStateOf(summarizedRecipeUiModel.isFavorite) }
	Card(elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)) {
		Box(modifier = modifier.clip(shape = MaterialTheme.shapes.medium)) {
			NeuracrImage(
				neuracrImageProperty = summarizedRecipeUiModel.imageProperty,
				maxImageHeight = (LocalConfiguration.current.screenWidthDp.dp - 64.dp) * 3 / 4,
				modifier = Modifier.fillMaxWidth()
			)
			Text(
				text = summarizedRecipeUiModel.title,
				color = MaterialTheme.colorScheme.onSurface,
				style = MaterialTheme.typography.titleSmall,
				modifier = Modifier
					.align(Alignment.BottomStart)
					.clip(
						shape = MaterialTheme.shapes.medium.copy(
							topStart = CornerSize(0.dp),
							bottomEnd = CornerSize(0.dp)
						)
					)
					.background(color = MaterialTheme.colorScheme.surface)
					.padding(start = 12.dp, bottom = 2.dp, end = 12.dp)
			)
			NeuracrFlag(
				neuracrFlagProperty = summarizedRecipeUiModel.flagProperty,
				modifier = Modifier
					.align(Alignment.TopEnd)
					.clip(
						shape = MaterialTheme.shapes.medium.copy(
							topStart = CornerSize(0.dp),
							bottomEnd = CornerSize(0.dp)
						)
					)
					.width(30.dp)
					.height(20.dp)
			)
			NeuracrLike(
				isFavorite = isFavorite,
				onFavoriteClick = {
					onFavoriteClick(summarizedRecipeUiModel)
					isFavorite = !isFavorite
				},
				modifier = Modifier.align(Alignment.BottomEnd)
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun SummarizedRecipeCardPreview() {
	HomeRecipeCard(summarizedRecipeSample, {})
}
