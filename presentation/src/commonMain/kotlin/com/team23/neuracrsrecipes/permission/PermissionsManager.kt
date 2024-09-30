package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable

expect class PermissionsManager(callback: PermissionCallback) : PermissionHandler

@Composable
expect fun createPermissionsManager(callback: PermissionCallback): PermissionsManager
