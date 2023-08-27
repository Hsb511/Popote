package com.team23.presentation.common.handlers

import androidx.navigation.NavHostController
import com.team23.presentation.common.handlers.AppPage.WithArgument
import com.team23.presentation.recipe.extensions.toCleanRecipeId

class NavigationHandler(
	private val navController: NavHostController,
) {
	fun openRecipe(recipeId: String) {
		navController.navigate("${WithArgument.Recipe.route}/${recipeId.toCleanRecipeId()}")
	}

	fun openSearch(tag: String) {
		navController.navigate("${WithArgument.Search.route}/$tag")
	}

	fun openAdd() {
		navController.navigate(AppPage.Upload.route)
	}

	fun navigateHomeWithRecipeDeleted(recipeTitle: String) {
		navController.navigate("${WithArgument.HomeWithDeletedRecipe.route}/${recipeTitle.replace(" ", "_")}")
	}
}