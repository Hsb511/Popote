package com.team23.presentation.drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.design_system.theming.NeuracrTheme
import com.team23.presentation.drawer.models.DrawerUiModel
import com.team23.presentation.drawer.views.DrawerFooter
import com.team23.presentation.drawer.views.DrawerHeader
import com.team23.presentation.drawer.views.NicknameDrawerItem

@Composable
fun ModalMenuDrawer(drawerUiModel: DrawerUiModel, modifier: Modifier = Modifier, content: @Composable () -> Unit) {
	ModalNavigationDrawer(
		modifier = modifier,
		drawerState = drawerUiModel.drawerState,
		gesturesEnabled = false,
		drawerContent = {
			ModalDrawerSheet {
				Spacer(modifier = Modifier.height(64.dp)) // TopAppBar height
				DrawerHeader(versionName = drawerUiModel.versionName)
				NicknameDrawerItem(nickname = drawerUiModel.nickname)
				Spacer(modifier = Modifier.weight(1f))
				DrawerFooter()
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
				versionName = "23.23.0",
				nickname = "GOGO"
			)
		) {}
	}
}
