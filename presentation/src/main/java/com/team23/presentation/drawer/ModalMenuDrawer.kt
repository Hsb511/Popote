package com.team23.presentation.drawer

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.drawer.models.DrawerUiModel
import com.team23.presentation.drawer.views.DrawerFooter
import com.team23.presentation.drawer.views.DrawerHeader

@Composable
fun ModalMenuDrawer(drawerUiModel: DrawerUiModel, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
	ModalNavigationDrawer(
		modifier = modifier,
		drawerState = drawerUiModel.drawerState,
		gesturesEnabled = false,
		drawerContent = {
			ModalDrawerSheet {
				Column(modifier = Modifier.fillMaxSize()) {
					DrawerHeader(versionName = drawerUiModel.versionName)

					Spacer(modifier = Modifier.weight(1f))
					DrawerFooter()
				}
			}
		},
		content = content,
	)
}

@Composable
@Preview(showSystemUi = true)
fun ModalMenuDrawerPreview() {
	NeuracrTheme {
		ModalMenuDrawer(
			DrawerUiModel(
				rememberDrawerState(initialValue = DrawerValue.Open),
				versionName = "23.23.0"
			)
		) {}
	}
}
