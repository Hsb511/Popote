package com.team23.presentation.drawer.models

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
data class DrawerUiModel(
	val drawerState: DrawerState,
	val versionName: String,
)
