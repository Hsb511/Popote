package com.team23.view.widget.drawer

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.team23.neuracrsrecipes.model.uimodel.DrawerUiModel
import com.team23.neuracrsrecipes.viewmodel.UserViewModel
import org.koin.compose.koinInject

@Composable
fun ModalMenuDrawer(
    drawerUiModel: DrawerUiModel,
    drawerState: DrawerState,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val userViewModel = koinInject<UserViewModel>()

    ModalNavigationDrawer(
        modifier = modifier,
        drawerState = drawerState,
        gesturesEnabled = false,
        drawerContent = {
            ModalMenuDrawerContent(
                nickname = userViewModel.nickname.collectAsState().value,
                versionName = drawerUiModel.versionName,
                onChangeNickname = userViewModel::onChangeLocalNickname,
            )
        },
        content = content,
    )
}

@Composable
private fun ModalMenuDrawerContent(
    nickname: String?,
    versionName: String,
    onChangeNickname: (String) -> Unit = {},
) {
    ModalDrawerSheet {
        Spacer(modifier = Modifier.height(64.dp)) // TopAppBar height
        NicknameDrawerItem(
            nickname = nickname,
            onChangeNickname = onChangeNickname,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .padding(top = 8.dp)
        )
        Spacer(modifier = Modifier.weight(1f))
        DrawerFooter(versionName = versionName)
        Spacer(modifier = Modifier.height(56.dp)) // BottomBar height
    }
}
