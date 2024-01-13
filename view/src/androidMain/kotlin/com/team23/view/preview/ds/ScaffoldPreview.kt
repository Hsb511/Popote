package com.team23.view.preview.ds

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.team23.view.ds.scaffold.BottomBar
import com.team23.view.ds.scaffold.PopoteScaffold
import com.team23.view.ds.scaffold.Snackbar
import com.team23.view.ds.scaffold.TopBar
import com.team23.view.navigation.NavItemProperty
import com.team23.view.theme.PopoteTheme

@Composable
@Preview(showBackground = true)
private fun TopBarPreview() {
    PopoteTheme {
        TopBar(
            isNavigationEmpty = true,
            heightToBeFaded = 0f,
            title = "Bretzels",
            scrollState = rememberScrollState(),
            navigateUp = {},
            drawerState = DrawerState(DrawerValue.Closed),
            openMenu = {},
            closeMenu = {},
        )
    }
}

@Composable
@Preview(showBackground = true)
fun SnackbarPreview() {
    PopoteTheme {
        Snackbar(
            snackbarData = object : SnackbarData {
                override val visuals = object : SnackbarVisuals {
                    override val message = "Tomato soup has been added to your favorites"
                    override val actionLabel = null
                    override val duration = SnackbarDuration.Indefinite
                    override val withDismissAction = false
                }

                override fun dismiss() {}
                override fun performAction() {}
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun BottomBarPreview() {
    PopoteTheme {
        BottomBar(
            listOf(
                NavItemProperty("Home", Icons.Filled.Home, true) {},
                NavItemProperty("Search", Icons.Outlined.Search, false) {},
                NavItemProperty("Add", Icons.Outlined.Add, false) {},
                NavItemProperty("Favorite", Icons.Outlined.Favorite, false) {},
            )
        ) {}
    }
}


@Composable
@Preview(showSystemUi = true)
fun PopoteScaffoldPreview() {
    PopoteTheme {
        PopoteScaffold(
            snackbarHostState = remember { SnackbarHostState() },
            scrollState = rememberScrollState(),
            heightToBeFaded = 0f,
            title = "Bretzels",
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
