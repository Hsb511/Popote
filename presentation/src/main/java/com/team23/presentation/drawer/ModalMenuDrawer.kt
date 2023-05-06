package com.team23.presentation.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.drawer.views.DrawerFooter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModalMenuDrawer(drawerState: DrawerState, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
	ModalNavigationDrawer(
		modifier = modifier,
		drawerState = drawerState,
		gesturesEnabled = false,
		drawerContent = {
			ModalDrawerSheet {
				Column(modifier = Modifier.fillMaxSize()) {
					Spacer(modifier = Modifier.weight(1f))
					DrawerFooter()
				}
			}
		},
		content = content,
	)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun ModalMenuDrawerPreview() {
	NeuracrTheme {
		ModalMenuDrawer(rememberDrawerState(initialValue = DrawerValue.Open)) {}
	}
}
