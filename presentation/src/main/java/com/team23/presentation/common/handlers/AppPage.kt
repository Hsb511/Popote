package com.team23.presentation.common.handlers

import androidx.annotation.StringRes
import com.team23.presentation.R

sealed class AppPage(open val route: String, @StringRes open val displayNameId: Int) {

	object Home : AppPage("home", R.string.navigation_home_display_name)
	object Search : AppPage("search", R.string.navigation_search_display_name)
	object Upload : AppPage("upload", R.string.navigation_upload_display_name)
	object Favorite : AppPage("favorite", R.string.navigation_favorite_display_name)
	sealed class WithArgument(
		override val route: String,
		@StringRes override val displayNameId: Int,
		val argumentName: String
	) : AppPage(route, displayNameId) {

		object Recipe : WithArgument("home", R.string.navigation_home_display_name, "recipeId")
		object Search : WithArgument("search", R.string.navigation_search_display_name, "tag")
		object HomeWithDeletedRecipe :  WithArgument("delete", R.string.navigation_home_display_name, "recipeTitle")
	}
}
