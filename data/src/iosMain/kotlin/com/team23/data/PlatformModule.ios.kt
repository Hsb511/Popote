package com.team23.data

import com.team23.data.datasource.DatabaseDriverFactory
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual fun platformModule() = module {
    singleOf(::DatabaseDriverFactory)
}
