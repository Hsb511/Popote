package com.team23.view

import androidx.compose.material3.SnackbarHostState
import com.team23.neuracrsrecipes.handler.SnackbarHandler
import com.team23.view.handler.ComposeSnackbarHandler
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModule = module {
    singleOf(::SnackbarHostState)
    singleOf(::ComposeSnackbarHandler) { bind<SnackbarHandler>() }
}
