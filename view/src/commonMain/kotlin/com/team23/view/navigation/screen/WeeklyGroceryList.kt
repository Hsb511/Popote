package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import com.team23.neuracrsrecipes.viewmodel.WeeklyGroceryListViewModel
import com.team23.view.widget.grocery.WeeklyGroceryListData
import com.team23.view.widget.grocery.WeeklyGroceryListEmpty
import org.koin.compose.koinInject


internal data object WeeklyGroceryListScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinInject<WeeklyGroceryListViewModel>()
        val uiState by viewModel.uiState.collectAsState()

        if (uiState.isEmpty) {
            WeeklyGroceryListEmpty()
        } else {
            WeeklyGroceryListData(
                uiModel = uiState,
                onAction = viewModel::onAction,
            )
        }
    }
}