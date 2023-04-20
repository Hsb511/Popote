package com.example.design_system.scaffold

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.R
import com.example.design_system.theming.NeuracrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
	isHome: Boolean,
	navigateUp: () -> Unit,
	openMenu: () -> Unit,
) {
	CenterAlignedTopAppBar(
		colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
			containerColor = MaterialTheme.colorScheme.primary,
			actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
		),
		navigationIcon = {
			if (isHome) {
				// TODO DISPLAY APP LOGO
			} else {
				IconButton(onClick = navigateUp) {
					Icon(
						imageVector = Icons.Filled.ArrowBack,
						contentDescription = stringResource(id = R.string.scaffold_navigate_up_a11y)
					)
				}
			}
		},
		title = {
			Text(
				text = stringResource(R.string.scaffold_title),
				style = MaterialTheme.typography.headlineSmall,
			)
		},
		actions = {
			IconButton(onClick = openMenu) {
				Icon(
					imageVector = Icons.Filled.Menu,
					contentDescription = stringResource(id = R.string.scaffold_menu_a11y),
					tint = MaterialTheme.colorScheme.onPrimary,
				)
			}
		}
	)
}

@Composable
@Preview(showBackground = true)
private fun TopBarPreview() {
	NeuracrTheme {
		TopBar(
			isHome = true,
			navigateUp = {},
			openMenu = {},
		)
	}
}
