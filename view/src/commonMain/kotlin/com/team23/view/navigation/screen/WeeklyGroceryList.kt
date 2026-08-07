package com.team23.view.navigation.screen

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.team23.view.widget.grocery.WeeklyGroceryListData


internal data object WeeklyGroceryListScreen : Screen {

    @Composable
    override fun Content() {

        WeeklyGroceryListData()
    }
}