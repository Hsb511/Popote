package com.team23.neuracrsrecipes.navigation

import androidx.annotation.StringRes
import com.team23.neuracrsrecipes.R

sealed class AppPage(open val route: String, @StringRes open val displayNameId: Int) {

	object Home : AppPage("home", R.string.navigation_home_display_name)
	object Search : AppPage("search", R.string.navigation_search_display_name)
	object About : AppPage("about", R.string.navigation_about_display_name)
	sealed class WithArgument(
		override val route: String,
		@StringRes override val displayNameId: Int,
		val argumentName: String
	) : AppPage(route, displayNameId) {

		object Recipe : WithArgument("home", R.string.navigation_home_display_name, "recipeId")
	}
}
