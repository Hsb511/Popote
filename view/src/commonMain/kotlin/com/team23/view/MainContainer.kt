package com.team23.view

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.navigator.CurrentScreen
import com.team23.neuracrsrecipes.model.uimodel.DrawerUiModel
import com.team23.view.ds.scaffold.PopoteScaffold
import com.team23.view.navigation.PopoteNavigator
import com.team23.view.navigation.createBottomNavItems
import com.team23.view.theme.PopoteTheme
import com.team23.view.widget.drawer.ModalMenuDrawer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.koin.compose.KoinContext
import org.koin.compose.koinInject

@Composable
fun MainContainer() {
    KoinContext {
        PopoteTheme {
            val scrollState = rememberScrollState()
            val heightToBeFaded = remember { mutableStateOf(0f) }
            val title: MutableState<String?>  = remember { mutableStateOf(null) }

            PopoteNavigator(scrollState, heightToBeFaded, title) { navigator ->
                val drawerState = rememberDrawerState(DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                val snackbarHostState = koinInject<SnackbarHostState>()

                val closeDrawer: () -> Unit = {
                    scope.launch(Dispatchers.IO) { drawerState.close() }
                }
                val navItemProperties = createBottomNavItems(
                    navigator, scrollState, heightToBeFaded, title, closeDrawer,
                )

                PopoteScaffold(
                    snackbarHostState = snackbarHostState,
                    scrollState = scrollState,
                    heightToBeFaded = heightToBeFaded.value,
                    title = title.value,
                    navItemProperties = navItemProperties,
                    navigateUp = navigator::pop,
                    drawerState = drawerState,
                    openMenu = { scope.launch(Dispatchers.IO) { drawerState.open() } },
                    closeMenu = { scope.launch(Dispatchers.IO) { drawerState.close() } },
                    isNavigationEmpty = navigator.isEmpty

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
