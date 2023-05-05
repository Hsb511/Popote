package com.team23.presentation.search.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.R
import com.team23.design_system.flags.NeuracrFlag
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImage
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@Composable
fun SearchRecipeCard(
	summarizedRecipeUiModel: SummarizedRecipeUiModel,
	modifier: Modifier = Modifier
) {
	Card(
		elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
		modifier = modifier.height(64.dp)
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.fillMaxSize(),
		) {
			Box(modifier = Modifier
				.width(64.dp)
				.fillMaxHeight()) {
				NeuracrImage(
					neuracrImageProperty = summarizedRecipeUiModel.imageProperty,
					maxImageHeight = 200.dp,
					hasNoCornerEnd = true,
					contentScale = ContentScale.FillHeight,
					modifier = Modifier.fillMaxHeight(),
				)
			}
			Box(
				modifier = Modifier.fillMaxSize()
			) {
				Text(
					text = summarizedRecipeUiModel.title,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.titleSmall,
					modifier = Modifier
						.padding(all = 8.dp)
						.align(Alignment.CenterStart)
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
			}
		}
	}
}

@Composable
@Preview(showBackground = true)
fun SearchRecipeCardPreview() {
	NeuracrTheme {
		SearchRecipeCard(
			SummarizedRecipeUiModel(
				id = "",
				imageProperty = NeuracrImageProperty.Resource(
					contentDescription = null,
					imageRes = R.drawable.bretzel
				),
				title = "Bretzels",
				flagProperty = NeuracrFlagProperty.FRENCH,
			),
		)
	}
}
