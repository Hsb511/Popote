package com.team23.view.navigation.screen

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.uistate.FavoriteUiState
import com.team23.neuracrsrecipes.viewmodel.FavoriteViewModel
import com.team23.view.LocalTitle
import com.team23.view.Res
import com.team23.view.dialog_confirm
import com.team23.view.dialog_dismiss
import com.team23.view.ds.button.ButtonTextDialog
import com.team23.view.favorite_delete_all_dialog_description
import com.team23.view.favorite_delete_all_dialog_title
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.favorite.FavoriteDataEmptyScreen
import com.team23.view.widget.favorite.FavoriteDataScreen
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject

internal data object FavoriteScreen : Screen {

    @Composable
    override fun Content() {
        val favoriteViewModel = koinInject<FavoriteViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow
        val openDialog = remember { mutableStateOf(false) }

        LocalTitle.current.value = null

        FavoriteScreen(
            favoriteUiState = favoriteViewModel.uiState.collectAsState().value,
            onRecipeClick = { recipeId -> appNavigator.navigateToRecipe(navigator, recipeId) },
            onFavoriteClick = { recipe -> favoriteViewModel.onFavoriteClick(recipe) },
            onDisplayClick = favoriteViewModel::onDisplayTypeClick,
            onLocalPhoneClick = favoriteViewModel::onLocalPhoneClick,
            onRemoveAllClick = { openDialog.value = true },
        )

        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = {
                    Text(
                        text = stringResource(Res.string.favorite_delete_all_dialog_title),
                        textAlign = TextAlign.Center
                    )
                },
                text = { Text(stringResource(Res.string.favorite_delete_all_dialog_description)) },
                confirmButton = {
                    ButtonTextDialog(
                        text = stringResource(Res.string.dialog_confirm),
                        onClick = {
                            openDialog.value = false
                            favoriteViewModel.onRemoveAllConfirm()
                        },
                    )
                },
                dismissButton = {
                    ButtonTextDialog(
                        text = stringResource(Res.string.dialog_dismiss),
                        onClick = {
                            openDialog.value = false
                        },
                    )
                }
            )
        }
    }
}

@Composable
fun FavoriteScreen(
    favoriteUiState: FavoriteUiState,
    onRecipeClick: (String) -> Unit,
    onFavoriteClick: (String) -> Unit,
    onDisplayClick: () -> Unit,
    onLocalPhoneClick: () -> Unit,
    onRemoveAllClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (favoriteUiState) {
        is FavoriteUiState.Data.WithFavorites -> FavoriteDataScreen(
            state = favoriteUiState,
            onRecipeClick = onRecipeClick,
            onFavoriteClick = onFavoriteClick,
            onDisplayClick = onDisplayClick,
            onLocalPhoneClick = onLocalPhoneClick,
            onRemoveAllClick = onRemoveAllClick,
            modifier = modifier,
        )

        is FavoriteUiState.Data.Empty -> FavoriteDataEmptyScreen(modifier = modifier)
        is FavoriteUiState.Loading -> {}
    }
}
