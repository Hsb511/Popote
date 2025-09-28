package com.team23.view

import androidx.compose.ui.window.ComposeUIViewController
import com.team23.domain.domainModule
import com.team23.data.dataModule
import com.team23.neuracrsrecipes.presentationModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val iosModule = module {
    viewModule
    presentationModule
    domainModule
}

fun MainContainerViewControllerKt() = ComposeUIViewController {
    startKoin {
        // modules(commonModule, dataModule, ...)
        modules(viewModule, presentationModule, domainModule, dataModule)
    }
    MainContainer()
}
