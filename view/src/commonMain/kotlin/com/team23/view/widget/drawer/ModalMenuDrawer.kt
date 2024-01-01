package com.team23.view.widget.drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.uimodel.DrawerUiModel

@Composable
fun ModalMenuDrawer(
    drawerUiModel: DrawerUiModel,
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    // val userViewModel: UserViewModel = hiltViewModel()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(64.dp)) // TopAppBar height
                NicknameDrawerItem(
                    nickname = null, // userViewModel.nickname.collectAsState().value,
                    onChangeNickname = { /* nickname -> userViewModel.onChangeLocalNickname(nickname) */ },
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                DrawerFooter(versionName = drawerUiModel.versionName)
            }
        },
        content = content,
    )
}

