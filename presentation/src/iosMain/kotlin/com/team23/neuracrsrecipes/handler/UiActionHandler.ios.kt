package com.team23.neuracrsrecipes.handler

import com.team23.neuracrsrecipes.model.action.UiAction
import platform.Foundation.NSURL
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class UiActionHandler {
    actual fun handle(action: UiAction) {
        when (action) {
            UiAction.LaunchSettings -> launchSettings()
            UiAction.RedirectToWebsite -> redirectToWebsite()
        }
    }

    private fun launchSettings() {
        NSURL.URLWithString(UIApplicationOpenSettingsURLString)?.let {
            UIApplication.sharedApplication.openURL(it)
        }
    }

    private fun redirectToWebsite() {
        NSURL.URLWithString("https://neuracr.github.io/")?.let { url ->
            UIApplication.sharedApplication.openURL(url)
        }
    }
}
