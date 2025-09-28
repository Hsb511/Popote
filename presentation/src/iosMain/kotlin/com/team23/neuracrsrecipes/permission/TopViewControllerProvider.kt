package com.team23.neuracrsrecipes.permission

import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.UIWindowScene

class TopViewControllerProvider {
    fun top(): UIViewController? {
        val app = UIApplication.sharedApplication
        val keyWindow = app.keyWindow
            ?: (app.connectedScenes.firstOrNull() as? UIWindowScene)?.keyWindow
        var top = keyWindow?.rootViewController
        while (top?.presentedViewController != null) {
            top = top.presentedViewController
        }
        return top
    }
}
