package com.team23.view.widget.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.view.ds.shimmer.Shimmer

@Composable
fun HomeContentLoading() {
	LazyColumn(
		contentPadding = PaddingValues(16.dp),
		verticalArrangement = Arrangement.spacedBy(16.dp),
		modifier = Modifier
			.fillMaxSize()
			.padding(bottom = 16.dp)
	) {
		item {
			Shimmer(
				textStyle = MaterialTheme.typography.displaySmall,
				modifier = Modifier
					.padding(start = 16.dp, top = 16.dp, end = 16.dp)
					.width(200.dp)
			)
		}
		items(List(3) {}) {
			Shimmer(
				modifier = Modifier
					.padding(horizontal = 16.dp)
					.fillMaxWidth()
					// .height(LocalConfiguration.current.screenWidthDp.dp * 3 / 4)
			)
		}
	}
}

