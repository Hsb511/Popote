package com.team23.view

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import com.team23.neuracrsrecipes.model.uimodel.DrawerUiModel
import com.team23.view.ds.scaffold.PopoteScaffold
import com.team23.view.navigation.PopoteNavigator
import com.team23.view.navigation.screen.AddScreen
import com.team23.view.navigation.screen.HomeScreen
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.drawer.ModalMenuDrawer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject
import org.koin.core.context.startKoin

@Composable
fun MainContainer() {
    KoinContext {
        PopoteTheme {
            PopoteNavigator { navigator ->
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val snackbarHostState = koinInject<SnackbarHostState>()

                PopoteScaffold(
                    snackbarHostState = snackbarHostState,
                    scrollState = rememberScrollState(),
                    heightToBeFaded = 8.9f,
                    title = null,
                    navItemProperties = listOf(),
                    navigateUp = {},
                    drawerState = drawerState,
                    openMenu = { scope.launch(Dispatchers.IO) { drawerState.open() } },
                    closeMenu = { scope.launch(Dispatchers.IO) { drawerState.close() } },
                    isNavigationEmpty = false

                ) {
                    ModalMenuDrawer(
                        drawerUiModel = DrawerUiModel("2.0.0"),
                        drawerState = drawerState,
                    ) {
                        CurrentScreen()
                    }
                }
            }
        }
    }
}
