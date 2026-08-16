package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import com.team23.neuracrsrecipes.viewmodel.GroceryListViewModel
import com.team23.view.widget.grocery.GroceryListData
import com.team23.view.widget.grocery.GroceryListEmpty
import org.koin.compose.koinInject


internal data object WeeklyGroceryListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinInject<GroceryListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        if (uiState.isEmpty) {
            GroceryListEmpty()
        } else {
            GroceryListData(
                uiModel = uiState,
                onAction = viewModel::onAction,
            )
        }
    }
}