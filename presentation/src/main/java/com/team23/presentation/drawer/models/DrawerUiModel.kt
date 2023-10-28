package com.team23.presentation.drawer.models

import androidx.compose.material3.DrawerState

data class DrawerUiModel(
	val drawerState: DrawerState,
	val versionName: String,
	val nickname: String?,
)
