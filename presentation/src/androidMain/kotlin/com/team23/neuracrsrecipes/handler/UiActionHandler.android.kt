package com.team23.neuracrsrecipes.handler

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import com.team23.neuracrsrecipes.model.action.UiAction

actual class UiActionHandler(
    private val context: Context,
) {
    actual fun handle(action: UiAction) {
        when (action) {
            is UiAction.RedirectToWebsite -> redirectToWebsite()
            is UiAction.LaunchSettings -> launchSettings()
        }
    }

    private fun redirectToWebsite() {
        Intent().apply {
            action = Intent.ACTION_VIEW
            data = Uri.parse("https://neuracr.github.io/")
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }.also { intent ->
            context.startActivity(intent)
        }
    }

    private fun launchSettings() {
        Intent(
            Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
            Uri.fromParts("package", context.packageName, null)
        ).also {
            context.startActivity(it)
        }
    }
}
