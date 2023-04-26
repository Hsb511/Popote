package com.example.design_system.wip

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.image.NeuracrImage
import com.example.design_system.image.NeuracrImageProperty
import com.example.design_system.theming.NeuracrTheme

@Composable
fun NeuracrPageInProgress(modifier: Modifier = Modifier) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.fillMaxSize()
			.padding(all = 32.dp)
	) {
		NeuracrImage(
			neuracrImageProperty = NeuracrImageProperty.Resource(null, R.drawable.neuracr_wip),
			maxImageHeight = 230.dp,
			modifier = Modifier.padding(bottom = 16.dp)
		)
		Text(
			text = stringResource(id = R.string.wip_page_message),
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxWidth()
		)
	}
}

@Composable
@Preview(showSystemUi = true)
fun NeuracrPageInProgressPreview() {
    NeuracrTheme {
	    NeuracrPageInProgress()
    }
}
