package com.team23.presentation.drawer.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.R
import com.team23.design_system.theming.NeuracrTheme

@Composable
fun DrawerHeader(
	versionName: String,
	modifier: Modifier = Modifier
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		modifier = modifier
			.padding(start = 4.dp, end = 8.dp)
	) {
		Image(
			contentScale = ContentScale.FillHeight,
			painter = painterResource(id = R.drawable.neuracr_logo),
			contentDescription = null,
			modifier = Modifier
				.height(60.dp)
				.padding(vertical = 6.dp)
		)
		Spacer(modifier = Modifier.weight(1f))
		Text(
			text = stringResource(R.string.scaffold_title),
			style = MaterialTheme.typography.headlineSmall,
		)
		Spacer(modifier = Modifier.weight(1f))
		Text(
			text = "v$versionName",
		)
	}
}

@Composable
@Preview(showBackground = true)
fun DrawerHeaderPreview() {
	NeuracrTheme {
		DrawerHeader(versionName = "23.23.0")
	}
}
