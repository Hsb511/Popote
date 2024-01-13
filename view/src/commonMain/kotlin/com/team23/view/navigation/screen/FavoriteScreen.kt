package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.neuracrsrecipes.viewmodel.FavoriteViewModel
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.favorite.FavoriteDataEmptyScreen
import com.team23.view.widget.favorite.FavoriteDataScreen
import org.koin.compose.koinInject

internal object FavoriteScreen: Screen {

	@Composable
	override fun Content() {
		val favoriteViewModel = koinInject<FavoriteViewModel>()
		val appNavigator = koinInject<AppNavigator>()
		val navigator = LocalNavigator.currentOrThrow
		val modifier = Modifier

		when (val favoriteUiState = favoriteViewModel.uiState.collectAsState().value) {
			is FavoriteUiState.Data.WithFavorites -> FavoriteDataScreen(
				state = favoriteUiState,
				onRecipeClick = { recipe -> appNavigator.navigateToRecipe(navigator, recipe.id) },
				onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe.id) },
				onDisplayClick = favoriteViewModel::onDisplayTypeClick,
				onLocalPhoneClick = favoriteViewModel::onLocalPhoneClick,
				modifier = modifier,
			)

			is FavoriteUiState.Data.Empty -> FavoriteDataEmptyScreen(modifier = modifier)
			is FavoriteUiState.Loading -> {}
		}
	}
}
