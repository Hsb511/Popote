package com.example.neuracrsrecipes.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.neuracrsrecipes.R

enum class AppPage(val route: String, @StringRes val displayNameId: Int, val icon: ImageVector) {
    HOME("home", R.string.navigation_home_display_name, Icons.Filled.Home),
    SEARCH("search", R.string.navigation_search_display_name, Icons.Filled.Search),
    ABOUT("about", R.string.navigation_about_display_name, Icons.Filled.Info),
}
