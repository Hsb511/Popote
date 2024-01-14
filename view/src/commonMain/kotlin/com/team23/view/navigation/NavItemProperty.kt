package com.team23.view.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.view.extension.stringResource
import com.team23.view.navigation.screen.AddScreen
import com.team23.view.navigation.screen.FavoriteScreen
import com.team23.view.navigation.screen.HomeScreen
import com.team23.view.navigation.screen.SearchScreen

data class NavItemProperty(
    val title: String,
    val icon: ImageVector,
    val isSelected: Boolean,
    val onNavigate: () -> Unit
)

@Composable
internal fun createBottomNavItems(
    navigator: Navigator,
    scrollState: ScrollState,
    heightToBeFaded: MutableState<Float>,
    title: MutableState<String?>,
    closeDrawer: () -> Unit,
): List<NavItemProperty> = listOf(
    with(isLastSelectedBottomScreen<HomeScreen>()) {
        createBottomNavItem(
            title = stringResource("navigation_home_display_name"),
            icon = if (this) Icons.Filled.Home else Icons.Outlined.Home,
            isSelected = this,
            screen = HomeScreen(scrollState, heightToBeFaded, title),
            navigator = navigator,
            closeDrawer = closeDrawer,
        )
    },
    with(isLastSelectedBottomScreen<SearchScreen>()) {
        createBottomNavItem(
            title = stringResource("navigation_search_display_name"),
            icon = if (this) Icons.Filled.Search else Icons.Outlined.Search,
            isSelected = this,
            screen = SearchScreen(scrollState, heightToBeFaded, title),
            navigator = navigator,
            closeDrawer = closeDrawer,
        )
    },
    with(isLastSelectedBottomScreen<AddScreen>()) {
        createBottomNavItem(
            title = stringResource("navigation_upload_display_name"),
            icon = if (this) Icons.Filled.AddCircle else Icons.Outlined.Add,
            isSelected = this,
            screen = AddScreen(scrollState, heightToBeFaded, title),
            navigator = navigator,
            closeDrawer = closeDrawer,
        )
    },
    with(isLastSelectedBottomScreen<FavoriteScreen>()) {
        createBottomNavItem(
            title = stringResource("navigation_favorite_display_name"),
            icon = if (this) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
            isSelected = this,
            screen = FavoriteScreen(scrollState, heightToBeFaded, title),
            navigator = navigator,
            closeDrawer = closeDrawer,
        )
    }
)

@Composable
private fun createBottomNavItem(
    title: String,
    icon: ImageVector,
    screen: Screen,
    isSelected: Boolean,
    navigator: Navigator,
    closeDrawer: () -> Unit,
): NavItemProperty = NavItemProperty(
    title = title,
    icon = icon,
    isSelected = isSelected,
    onNavigate = {
        navigator.push(screen)
        closeDrawer()
    },
)

@Composable
private inline fun <reified T> isLastSelectedBottomScreen(): Boolean {
    val navigator = LocalNavigator.currentOrThrow
    val lastBottomScreen = navigator.items
        .last { it is HomeScreen || it is SearchScreen || it is AddScreen || it is FavoriteScreen }

    return lastBottomScreen is T
}
