package com.team23.view.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.MutableState
import cafe.adriel.voyager.navigator.Navigator
import com.team23.view.navigation.screen.AddScreen
import com.team23.view.navigation.screen.HomeScreen
import com.team23.view.navigation.screen.RecipeScreen
import com.team23.view.navigation.screen.SearchScreen

class AppNavigator {

    fun navigateToHome(
        navigator: Navigator,
        scrollState: ScrollState,
        heightToBeFaded: MutableState<Float>,
        title: MutableState<String?>,
    ) {
        navigator.push(HomeScreen(scrollState, heightToBeFaded, title))
    }

    fun navigateToRecipe(
        navigator: Navigator,
        recipeId: String,
        scrollState: ScrollState,
        heightToBeFaded: MutableState<Float>,
        title: MutableState<String?>,
    ) {
        navigator.push(RecipeScreen(recipeId, scrollState, heightToBeFaded, title))
    }

    fun navigateToSearch(
        navigator: Navigator, tagId: String? = null,
        scrollState: ScrollState,
        heightToBeFaded: MutableState<Float>,
        title: MutableState<String?>,
    ) {
        navigator.push(SearchScreen(scrollState, heightToBeFaded, title, tagId))
    }

    fun navigateToAdd(
        navigator: Navigator,
        scrollState: ScrollState,
        heightToBeFaded: MutableState<Float>,
        title: MutableState<String?>,
    ) {
        navigator.push(AddScreen(scrollState, heightToBeFaded, title))
    }
}
