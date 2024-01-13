package com.team23.view.navigation.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.property.ImageProperty
import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.view.ds.image.NeuracrImage
import com.team23.view.extension.stringResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch



@Composable
fun ErrorScreen(errorUiModel: ErrorUiModel, modifier: Modifier = Modifier) {
	var isErrorShown by remember { mutableStateOf(false) }
	val scrollState = rememberScrollState()
	val scope = rememberCoroutineScope()
	Column(
		verticalArrangement = Arrangement.spacedBy(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.fillMaxSize()
			.verticalScroll(scrollState)
			.padding(all = 32.dp)
	) {
		NeuracrImage(
			neuracrImageProperty = ImageProperty.Resource(null, "drawable/neuracr_error.png"),
			maxImageHeight = 230.dp,
			modifier = Modifier.padding(top = 32.dp)
		)
		Text(
			text = stringResource(id = "error_title"),
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = stringResource(id = "error_description"),
			style = MaterialTheme.typography.bodyLarge,
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxWidth()
		)
		ElevatedButton(
			colors = ButtonDefaults.elevatedButtonColors(
				containerColor = MaterialTheme.colorScheme.secondary,
				contentColor = MaterialTheme.colorScheme.onSecondary,
			),
			onClick = errorUiModel.redirectToWebsite,
		) {
			Text(
				text = stringResource(id = "error_website_button"),
				style = MaterialTheme.typography.labelLarge,
			)
			Icon(
				imageVector = Icons.Filled.Send,
				contentDescription = null,
				modifier = Modifier.padding(start = 8.dp)
			)
		}
		OutlinedButton(
			border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
			onClick = {
				isErrorShown = !isErrorShown
				scope.launch(Dispatchers.IO) {
					scrollState.scrollTo(Int.MAX_VALUE)
				}
			},
		) {
			Text(
				text = stringResource(id = "error_expand"),
				style = MaterialTheme.typography.labelLarge,
				color = MaterialTheme.colorScheme.onBackground,
			)
			Icon(
				imageVector = if (isErrorShown) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
				contentDescription = null,
				modifier = Modifier.padding(start = 8.dp),
				tint = MaterialTheme.colorScheme.onBackground,
			)
		}
		if (isErrorShown) {
			Text(
				text = errorUiModel.message,
				style = MaterialTheme.typography.bodySmall
			)
		}
	}
}
