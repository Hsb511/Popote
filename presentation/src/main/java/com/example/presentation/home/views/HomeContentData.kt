package com.example.presentation.home.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.flags.NeuracrFlagProperty
import com.example.design_system.image.NeuracrImageProperty
import com.example.design_system.theming.NeuracrTheme
import com.example.presentation.R
import com.example.presentation.home.models.HomeRecipeUiModel

@Composable
fun HomeContentData(homeRecipeUiModels: List<HomeRecipeUiModel>, modifier: Modifier = Modifier) {

	LazyColumn(
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier.fillMaxSize()
	) {
		item {
			Text(
				text = stringResource(id = R.string.home_title),
				style = MaterialTheme.typography.displaySmall,
				color = MaterialTheme.colorScheme.onBackground,
				modifier = Modifier.padding(start = 32.dp, top = 16.dp, end = 32.dp)
			)
		}
		items(homeRecipeUiModels) { homeRecipeUiModel ->
			HomeRecipeCard(
				homeRecipeUiModel = homeRecipeUiModel,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 32.dp)
			)
		}
		item {
			Surface(color = Color.Transparent, modifier = Modifier.height(0.dp)) {}
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun HomeContentDataPreview() {
	NeuracrTheme {
		HomeContentData(
			homeRecipeUiModels = List(6) {
				HomeRecipeUiModel(
					id = "",
					title = "bretzels",
					imageProperty = NeuracrImageProperty.Resource(
						contentDescription = null,
						imageRes = com.example.design_system.R.drawable.bretzel
					),
					flagProperty = NeuracrFlagProperty.FRENCH,
				)
			}
		)
	}
}