package com.example.presentation.home.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.shimmer.NeuracrShimmer
import com.example.design_system.theming.NeuracrTheme

@Composable
fun HomeContentLoading(modifier: Modifier = Modifier) {
	LazyColumn(
		contentPadding = PaddingValues(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = modifier
			.fillMaxSize()
			.padding(bottom = 16.dp)
	) {
		item {
			NeuracrShimmer(
				textStyle = MaterialTheme.typography.displaySmall,
				modifier = Modifier
					.padding(start = 16.dp, top = 16.dp, end = 16.dp)
					.width(200.dp)
			)
		}
		items(List(3) {}) {
			NeuracrShimmer(
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.fillMaxWidth()
					.height(LocalConfiguration.current.screenWidthDp.dp * 3 / 4)
			)
		}
	}
}

@Composable
@Preview(showBackground = true)
private fun HomeContentLoadingPreview() {
	NeuracrTheme {
		HomeContentLoading()
	}
}
