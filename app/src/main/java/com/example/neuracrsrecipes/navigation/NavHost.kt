package com.example.neuracrsrecipes.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.design_system.scaffold.NavItemProperty
import com.example.design_system.scaffold.NeuracrScaffold
import com.example.presentation.home.views.HomeScreen

@Composable
internal fun NavHost(context: Context) {
    val navController = rememberNavController()
    val navItems = toNavItemProperties(AppPage.values().toList(), context, navController)
    NeuracrScaffold(navItems) { padding ->
        val paddingModifier = Modifier.padding(padding)
        NavHost(navController = navController, startDestination = AppPage.HOME.route) {
            composable(AppPage.HOME.route) { HomeScreen(modifier = paddingModifier) }
            composable(AppPage.SEARCH.route) {}
            composable(AppPage.ABOUT.route) {}
        }
    }
}

@Composable
internal fun toNavItemProperties(appPages: List<AppPage>, context: Context, navController: NavHostController): List<NavItemProperty> =
    appPages.map { appPage ->
        val currentScreenRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: AppPage.HOME.route
        NavItemProperty(
            title = context.getString(appPage.displayNameId),
            icon = appPage.icon,
            isSelected = currentScreenRoute == appPage.route,
            onNavigate = { navController.navigate(appPage.route) },
        )
    }
