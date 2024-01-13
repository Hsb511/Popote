package com.team23.view.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.MutableState
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

    fun navigateToSearch(navigator: Navigator, tagId: String? = null) {
        navigator.push(SearchScreen(selectedTag = tagId))
    }

    fun navigateToAdd(navigator: Navigator, scrollState: ScrollState, heightToBeFaded: MutableState<Float>) {
        navigator.push(AddScreen(scrollState, heightToBeFaded))
    }
}
