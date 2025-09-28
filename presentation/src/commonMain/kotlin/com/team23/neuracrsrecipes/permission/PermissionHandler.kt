package com.team23.neuracrsrecipes.permission

import androidx.compose.runtime.Composable

interface PermissionHandler {
    @Suppress("ComposableNaming")
    @Composable
    fun askPermission(permission: PermissionType)

    @Composable
    fun isPermissionGranted(permission: PermissionType): Boolean

}
