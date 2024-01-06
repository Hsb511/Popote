package com.team23.neuracrsrecipes

import com.team23.neuracrsrecipes.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.plus
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val presentationModule = module {
    factory { MainScope() + CoroutineName("user") }
    factoryOf(::UserViewModel)
}
