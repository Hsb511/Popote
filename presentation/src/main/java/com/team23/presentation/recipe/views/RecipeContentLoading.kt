package com.team23.presentation.recipe.views

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.team23.design_system.shimmer.NeuracrShimmer
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun RecipeContentLoading(modifier: Modifier = Modifier) {
	Column(modifier = modifier.padding(horizontal = 32.dp, vertical = 16.dp)) {
		NeuracrShimmer(
			textStyle = MaterialTheme.typography.displaySmall,
			modifier = Modifier.fillMaxWidth().padding(bottom = 4.dp)
		)
		Divider(modifier = Modifier.padding(top = 8.dp))
		NeuracrShimmer(
			fontSize = 14.sp,
			modifier = Modifier
				.padding(vertical = 4.dp)
				.width(300.dp)
		)
		Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
			List(3) {}.forEach { _ ->
				NeuracrShimmer(
					modifier = Modifier
						.height(32.dp)
						.width(80.dp)
				)
			}
		}
		NeuracrShimmer(
			modifier = Modifier
				.padding(vertical = 8.dp)
				.fillMaxWidth()
				.height(LocalConfiguration.current.screenWidthDp.dp * 3 / 4)
		)
		NeuracrShimmer(
			textStyle = MaterialTheme.typography.headlineSmall,
			modifier = Modifier
				.width(160.dp)
				.padding(top = 16.dp, bottom = 8.dp)
		)
		NeuracrShimmer(
			modifier = Modifier.fillMaxWidth().height(1000.dp)
		)
	}
}

@Composable
@Preview(showSystemUi = true)
fun RecipeContentLoadingPreview() {
	NeuracrTheme {
		RecipeContentLoading()
	}
}
