package com.team23.neuracrsrecipes

import com.team23.neuracrsrecipes.handler.UiActionHandler
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal actual fun platformModule(): Module = module {
    singleOf(::UiActionHandler)
}
