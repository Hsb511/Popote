package com.team23.view.navigation

import cafe.adriel.voyager.navigator.Navigator
import com.team23.view.navigation.screen.AddScreen
import com.team23.view.navigation.screen.HomeScreen
import com.team23.view.navigation.screen.RecipeScreen
import com.team23.view.navigation.screen.SearchScreen

class AppNavigator {

    fun navigateToHome(navigator: Navigator) {
        navigator.push(HomeScreen)
    }

    fun navigateToRecipe(navigator: Navigator, recipeId: String) {
        navigator.push(RecipeScreen(recipeId))
    }

    fun navigateToSearch(navigator: Navigator, selectedTag: String?) {
        navigator.push(SearchScreen(selectedTag))
    }

    fun navigateToAdd(navigator: Navigator) {
        navigator.push(AddScreen)
    }
}
