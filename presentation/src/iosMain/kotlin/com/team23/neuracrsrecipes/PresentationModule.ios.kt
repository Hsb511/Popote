package com.team23.neuracrsrecipes

import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.team23.neuracrsrecipes.handler.UiActionHandler

internal actual fun platformModule(): Module = module {
    singleOf(::UiActionHandler)
}
