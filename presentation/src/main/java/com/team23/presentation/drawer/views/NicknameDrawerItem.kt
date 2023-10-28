package com.team23.presentation.drawer.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.presentation.R

@Composable
fun NicknameDrawerItem(nickname: String?) {
	NavigationDrawerItem(
		label = {
			val text = if (nickname == null) {
				stringResource(id = R.string.drawer_empty_name)
			} else {
				stringResource(id = R.string.drawer_name_set, nickname)
			}
			Text(text = text)
		},
		icon = {
			Icon(imageVector = Icons.Filled.Face, contentDescription = null)
		},
		selected = false,
		colors = NavigationDrawerItemDefaults.colors(
			unselectedContainerColor = MaterialTheme.colorScheme.surfaceVariant
		),
		onClick = {},
		modifier = Modifier.padding(horizontal = 8.dp)
	)
}

@Composable
@Preview(showBackground = true)
fun NicknameDrawerItemPreview() {
	MaterialTheme {
		NicknameDrawerItem("GOGO")
	}
}
