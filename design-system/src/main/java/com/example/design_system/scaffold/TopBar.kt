package com.example.design_system.scaffold

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.design_system.R
import com.example.design_system.theming.NeuracrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopBar(
	isNavigationEmpty: Boolean,
	navigateUp: () -> Unit,
	drawerState: DrawerState,
	openMenu: () -> Unit,
	closeMenu: () -> Unit,
) {
	CenterAlignedTopAppBar(
		colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
			containerColor = MaterialTheme.colorScheme.primary,
			actionIconContentColor = MaterialTheme.colorScheme.onPrimary,
		),
		navigationIcon = {
			if (isNavigationEmpty) {
				Image(
					contentScale = ContentScale.FillHeight,
					painter = painterResource(id = R.drawable.neuracr_logo),
					contentDescription = null,
					modifier = Modifier
						.height(60.dp)
						.padding(vertical = 6.dp)
				)
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
			when (drawerState.currentValue) {
				DrawerValue.Closed ->
					IconButton(onClick = openMenu) {
						Icon(
							imageVector = Icons.Filled.Menu,
							contentDescription = stringResource(id = R.string.scaffold_open_menu_a11y),
							tint = MaterialTheme.colorScheme.onPrimary,
						)
					}
				DrawerValue.Open ->
					IconButton(onClick = closeMenu) {
						Icon(
							imageVector = Icons.Filled.Close,
							contentDescription = stringResource(id = R.string.scaffold_close_menu_a11y),
							tint = MaterialTheme.colorScheme.onPrimary,
						)
					}
			}
		}
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
private fun TopBarPreview() {
	NeuracrTheme {
		TopBar(
			isNavigationEmpty = true,
			navigateUp = {},
			drawerState = DrawerState(DrawerValue.Closed),
			openMenu = {},
			closeMenu = {},
		)
	}
}
