package com.team23.neuracrsrecipes.handler

import com.team23.neuracrsrecipes.model.action.UiAction
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

actual class UiActionHandler {
    actual fun handle(action: UiAction) {
    }

    fun launchSettings() {
        NSURL.URLWithString(UIApplicationOpenSettingsURLString)?.let {
            UIApplication.sharedApplication.openURL(it)
        }
    }
}