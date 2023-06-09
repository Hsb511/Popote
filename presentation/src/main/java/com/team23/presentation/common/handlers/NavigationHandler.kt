package com.team23.presentation.common.handlers

import androidx.navigation.NavHostController
import com.team23.presentation.recipe.extensions.toCleanRecipeId

class NavigationHandler(
	private val navController: NavHostController,
) {
	fun openRecipe(recipeId: String) {
		navController.navigate("${AppPage.WithArgument.Recipe.route}/${recipeId.toCleanRecipeId()}")
	}

	fun openSearch(tag: String) {
		navController.navigate("${AppPage.WithArgument.Search.route}/$tag")
	}
}