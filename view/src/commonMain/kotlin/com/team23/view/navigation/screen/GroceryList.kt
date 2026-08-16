package com.team23.view.navigation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.team23.neuracrsrecipes.model.event.GroceryUiEvent
import com.team23.neuracrsrecipes.viewmodel.GroceryListViewModel
import com.team23.view.LocalTitle
import com.team23.view.navigation.AppNavigator
import com.team23.view.widget.grocery.GroceryListData
import com.team23.view.widget.grocery.GroceryListEmpty
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.koinInject


internal data object GroceryListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinInject<GroceryListViewModel>()
        val appNavigator = koinInject<AppNavigator>()
        val navigator = LocalNavigator.currentOrThrow
        val clipboardManager = LocalClipboardManager.current
        val uiState by viewModel.uiState.collectAsState()

        LocalTitle.current.value = null

        FadingVisibility(uiState.isLoading) {
            GroceryListLoading()
        }

        FadingVisibility(uiState.isEmpty) {
            GroceryListEmpty()
        }

        FadingVisibility(!uiState.isLoading && !uiState.isEmpty) {
            GroceryListData(
                uiModel = uiState,
                onAction = viewModel::onAction,
            )
        }

        LaunchedEffect(true) {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    is GroceryUiEvent.OpenRecipe -> appNavigator.navigateToRecipe(navigator, event.recipeId)
                    is GroceryUiEvent.CopyIngredients -> clipboardManager.setText(AnnotatedString(event.rawText))
                }
            }
        }
    }
}

@Composable
private fun FadingVisibility(
    visible: Boolean,
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        content()
    }
}

@Composable
private fun GroceryListLoading() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.size(92.dp)
        )
    }
}
