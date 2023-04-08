package com.example.neuracrsrecipes.navigation

import androidx.annotation.StringRes
import com.example.neuracrsrecipes.R

enum class AppPage(val route: String, @StringRes val displayNameId: Int) {
	HOME("home", R.string.navigation_home_display_name),
	SEARCH("search", R.string.navigation_search_display_name),
	ABOUT("about", R.string.navigation_about_display_name),
}
