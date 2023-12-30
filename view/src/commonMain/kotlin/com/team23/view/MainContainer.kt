package com.team23.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.rememberDrawerState
import com.team23.view.theme.PopoteTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.team23.neuracrsrecipes.model.uimodel.ErrorUiModel
import com.team23.view.ds.scaffold.PopoteScaffold

@Composable
fun MainContainer() {
    PopoteTheme {
        PopoteScaffold(
            snackbarHostState = SnackbarHostState(),
            scrollState = rememberScrollState(),
            heightToBeFaded = 8.9f,
            title = null,
            navItemProperties = listOf(),
            navigateUp = {},
            drawerState = rememberDrawerState(DrawerValue.Closed),
            openMenu = {},
            closeMenu = {},
            isNavigationEmpty = false

        ) {
            ErrorScreen(
                errorUiModel = ErrorUiModel(
                    message = "volumus",
                    redirectToWebsite = {}
                ),
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
