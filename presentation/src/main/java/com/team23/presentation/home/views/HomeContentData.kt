package com.team23.presentation.home.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.flags.NeuracrFlagProperty
import com.team23.design_system.image.NeuracrImageProperty
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.R
import com.team23.presentation.home.models.SummarizedRecipeUiModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeContentData(
	summarizedRecipeUiModels: List<SummarizedRecipeUiModel>,
	homeRecipeClick: (SummarizedRecipeUiModel) -> Unit,
	modifier: Modifier = Modifier) {
	LazyVerticalStaggeredGrid(
		columns = StaggeredGridCells.Adaptive(300.dp),
		contentPadding = PaddingValues(32.dp),
		verticalItemSpacing = 16.dp,
		horizontalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier.fillMaxSize()
	) {
		item(span = StaggeredGridItemSpan.FullLine) {
			Text(
				text = stringResource(id = R.string.home_title),
				style = MaterialTheme.typography.displaySmall,
				color = MaterialTheme.colorScheme.onBackground,
			)
		}
		items(summarizedRecipeUiModels) { homeRecipeUiModel ->
			HomeRecipeCard(
				summarizedRecipeUiModel = homeRecipeUiModel,
				modifier = Modifier
					.fillMaxWidth()
					.clickable {
						homeRecipeClick(homeRecipeUiModel)
					}
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun HomeContentDataPreview() {
	NeuracrTheme {
		HomeContentData(
			summarizedRecipeUiModels = List(6) {
				SummarizedRecipeUiModel(
					id = "",
					title = "bretzels",
					imageProperty = NeuracrImageProperty.Resource(
						contentDescription = null,
						imageRes = com.team23.design_system.R.drawable.bretzel
					),
					flagProperty = NeuracrFlagProperty.FRENCH,
				)
			},
			{ }
		)
	}
}
