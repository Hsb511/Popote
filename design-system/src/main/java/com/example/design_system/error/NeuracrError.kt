package com.example.design_system.error

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.image.NeuracrImage
import com.example.design_system.image.NeuracrImageProperty
import com.example.design_system.theming.NeuracrTheme

@Composable
fun NeuracrError(message: String, modifier: Modifier = Modifier) {
	val context = LocalContext.current
	var isErrorShown by remember { mutableStateOf(false) }
	Column(
		verticalArrangement = Arrangement.spacedBy(16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = modifier
			.fillMaxSize()
			.padding(all = 32.dp)
	) {
		NeuracrImage(
			neuracrImageProperty = NeuracrImageProperty.Resource(null, R.drawable.neuracr_error),
			maxImageHeight = 230.dp,
			modifier = Modifier.padding(top = 32.dp)
		)
		Text(
			text = stringResource(id = R.string.error_title),
			style = MaterialTheme.typography.headlineSmall,
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxWidth()
		)
		Text(
			text = stringResource(id = R.string.error_description),
			style = MaterialTheme.typography.bodyLarge,
			textAlign = TextAlign.Center,
			modifier = Modifier.fillMaxWidth()
		)
		ElevatedButton(
			colors = ButtonDefaults.elevatedButtonColors(
				containerColor = MaterialTheme.colorScheme.secondary,
				contentColor = MaterialTheme.colorScheme.onSecondary,
			),
			onClick = {
				Intent().apply {
					action = Intent.ACTION_VIEW
					data = Uri.parse("https://neuracr.github.io/")
				}.also { intent ->
					context.startActivity(intent)
				}
			}) {
			Text(
				text = stringResource(id = R.string.error_website_button)
			)
			Icon(
				imageVector = Icons.Filled.Send,
				contentDescription = null,
				modifier = Modifier.padding(start = 8.dp)
			)
		}
		OutlinedButton(
			border = BorderStroke(width = 1.dp, color = MaterialTheme.colorScheme.secondary),
			onClick = { isErrorShown = !isErrorShown },
		) {
			Text(
				text = stringResource(id = R.string.error_expand),
				style = MaterialTheme.typography.bodySmall
			)
			Icon(
				imageVector = if (isErrorShown) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
				contentDescription = null,
				modifier = Modifier.padding(start = 8.dp)
			)
		}
		if (isErrorShown) {
			Text(
				text = message,
				style = MaterialTheme.typography.bodySmall
			)
		}
	}
}

@Composable
@Preview(showSystemUi = true)
fun NeuracrErrorPreview() {
	NeuracrTheme {
		NeuracrError("An error occured")
	}
}
