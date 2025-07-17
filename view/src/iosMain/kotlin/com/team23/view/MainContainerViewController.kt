package com.team23.view

import platform.UIKit.UIViewController
import androidx.compose.ui.window.ComposeUIViewController
import kotlin.experimental.ExperimentalObjCName

@OptIn(ExperimentalObjCName::class)
@ObjCName(
    name = "MainContainerViewController",
    swiftName = "MainContainerViewController",
)
fun MainContainerViewController(): UIViewController =
    ComposeUIViewController {
        MainContainer()
    }