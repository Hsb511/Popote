package com.team23.view.navigation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.extension.stringResource

@Composable
fun InProgressScreen(modifier: Modifier = Modifier) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.fillMaxSize()
			.padding(all = 32.dp)
	) {
		NeuracrImage(
			neuracrImageProperty = ImageProperty.Resource(null, "drawable/neuracr_wip.png"),
			maxImageHeight = 230.dp,
			modifier = Modifier.padding(bottom = 16.dp)
		)
		Text(
			text = stringResource(id = "wip_page_message"),
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxWidth()
		)
	}
}
