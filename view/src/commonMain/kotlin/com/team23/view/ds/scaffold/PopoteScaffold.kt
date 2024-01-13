package com.team23.view.ds.scaffold

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.view.navigation.NavItemProperty

@Composable
fun PopoteScaffold(
	snackbarHostState: SnackbarHostState,
	scrollState: ScrollState,
	heightToBeFaded: Float,
	title: String?,
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
				scrollState = scrollState,
				heightToBeFaded = heightToBeFaded,
				title = title,
				navigateUp = navigateUp,
				drawerState = drawerState,
				openMenu = openMenu,
				closeMenu = closeMenu,
			)
		},
		bottomBar = {
			BottomBar(
				navItemProperties = navItemProperties,
				closeMenu = closeMenu,
			)
		},
		snackbarHost = {
			NeuracrSnackbarHost(snackbarHostState)
		},
		modifier = Modifier.fillMaxSize()
	)
}
