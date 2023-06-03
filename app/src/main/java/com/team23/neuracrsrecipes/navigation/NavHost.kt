package com.team23.neuracrsrecipes.navigation

import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.team23.design_system.scaffold.NavItemProperty
import com.team23.design_system.scaffold.NeuracrScaffold
import com.team23.design_system.wip.NeuracrPageInProgress
import com.team23.neuracrsrecipes.BuildConfig
import com.team23.presentation.drawer.ModalMenuDrawer
import com.team23.presentation.drawer.models.DrawerUiModel
import com.team23.presentation.favorite.FavoriteScreen
import com.team23.presentation.home.HomeScreen
import com.team23.presentation.recipe.RecipeScreen
import com.team23.presentation.search.SearchScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun NavHost(context: Context) {
	val navController = rememberNavController()
	val navigator = Navigator(navController)
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()
	val navItems = toNavItemProperties(
		listOf(AppPage.Home, AppPage.Search, AppPage.Upload, AppPage.Favorite),
		context,
		navController,
		scope,
		drawerState
	)
	NeuracrScaffold(
		navItemProperties = navItems,
		navigateUp = {
			navController.navigateUp()
			scope.launch(Dispatchers.IO) { drawerState.close() }
		},
		isNavigationEmpty = navController.previousBackStackEntry == null,
		drawerState = drawerState,
		openMenu = { scope.launch(Dispatchers.IO) { drawerState.open() } },
		closeMenu = { scope.launch(Dispatchers.IO) { drawerState.close() } },
	) { padding ->
		ModalMenuDrawer(DrawerUiModel(drawerState, BuildConfig.VERSION_NAME), Modifier.padding(padding)) {
			NavHost(navController = navController, startDestination = AppPage.Home.route) {
				composable(route = AppPage.Home.route) {
					HomeScreen(
						onRecipeClick = { homeRecipeUiModel -> navigator.openRecipe(homeRecipeUiModel.id) }
					)
				}
				composable(
					route = "${AppPage.WithArgument.Recipe.route}/{${AppPage.WithArgument.Recipe.argumentName}}",
					arguments = listOf(navArgument(AppPage.WithArgument.Recipe.argumentName) {
						type = NavType.StringType
					})
				) { navBackStackEntry ->
					RecipeScreen(
						cleanRecipeId = navBackStackEntry.arguments?.getString(AppPage.WithArgument.Recipe.argumentName),
						onTagClicked = { tag -> navigator.openSearch(tag) }
					)
				}
				composable(route = AppPage.Search.route) {
					SearchScreen(
						onRecipeClick = { recipeUiModel -> navigator.openRecipe(recipeUiModel.id) }
					)
				}
				composable(route = "${AppPage.Search.route}/{${AppPage.WithArgument.Search.argumentName}}") { navBackStackEntry ->
					SearchScreen(
						onRecipeClick = { recipeUiModel -> navigator.openRecipe(recipeUiModel.id) },
						selectedTag = navBackStackEntry.arguments?.getString(AppPage.WithArgument.Search.argumentName),
					)
				}
				composable(route = AppPage.Upload.route) {
					NeuracrPageInProgress(Modifier.padding(padding))
				}

				composable(route = AppPage.Favorite.route) {
					FavoriteScreen(
						onRecipeClick = { recipeUiModel -> navigator.openRecipe(recipeUiModel.id) },
					)
				}
			}
		}
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun toNavItemProperties(
	appPages: List<AppPage>,
	context: Context,
	navController: NavHostController,
	scope: CoroutineScope,
	drawerState: DrawerState,
): List<NavItemProperty> =
	appPages.map { appPage ->
		val currentScreenRoute = navController.currentBackStackEntryAsState().value?.destination?.route
			?: AppPage.Home.route
		val isSelected = currentScreenRoute == appPage.route ||
			(appPage == AppPage.Home && currentScreenRoute.contains(AppPage.Home.route)) ||
			(appPage == AppPage.Search && currentScreenRoute.contains(AppPage.Search.route))
		val icon = when (appPage) {
			AppPage.Home -> if (isSelected) Icons.Filled.Home else Icons.Outlined.Home
			AppPage.Search -> if (isSelected) Icons.Filled.Search else Icons.Outlined.Search
			AppPage.Upload -> if (isSelected) Icons.Filled.AddCircle else Icons.Outlined.Add
			AppPage.Favorite -> if (isSelected) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
			else -> Icons.Filled.Done
		}
		NavItemProperty(
			title = context.getString(appPage.displayNameId),
			icon = icon,
			isSelected = isSelected,
			onNavigate = {
				navController.navigate(appPage.route)
				scope.launch(Dispatchers.IO) { drawerState.close() }
			},
		)
	}
