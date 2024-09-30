package com.team23.neuracrsrecipes.handler

import androidx.compose.runtime.Composable
import com.team23.neuracrsrecipes.model.action.UiAction
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

actual class UiActionHandler actual constructor() {
    actual fun handle(action: UiAction) {
    }

    fun launchSettings() {
        NSURL.URLWithString(UIApplicationOpenSettingsURLString)?.let {
            UIApplication.sharedApplication.openURL(it)
        }
    }
}