package com.example.design_system.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.design_system.theming.NeuracrTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NeuracrScaffold(
	navItemProperties: List<NavItemProperty>,
	navigateUp: () -> Unit,
	drawerState: DrawerState,
	openMenu: () -> Unit,
	closeMenu: () -> Unit,
	isNavigationEmpty: Boolean,
	content: @Composable (PaddingValues) -> Unit
) {
	Scaffold(
		content = content,
		topBar = {
			TopBar(
				isNavigationEmpty = isNavigationEmpty,
				navigateUp = navigateUp,
				drawerState = drawerState,
				openMenu = openMenu,
				closeMenu = closeMenu,
			)
		},
		bottomBar = { BottomBar(navItemProperties) },
		floatingActionButton = { FloatingActionButton() },
		modifier = Modifier.fillMaxSize()
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun NeuracrScaffoldPreview() {
	NeuracrTheme {
		NeuracrScaffold(
			listOf(
				NavItemProperty("Home", Icons.Filled.Home, true) {},
				NavItemProperty("Search", Icons.Filled.Search, false) {},
				NavItemProperty("About", Icons.Filled.Info, false) {},
			),
			{},
			drawerState = DrawerState(DrawerValue.Closed),
			openMenu = {},
			closeMenu = {},
			isNavigationEmpty = true,
		) {}
	}
}
