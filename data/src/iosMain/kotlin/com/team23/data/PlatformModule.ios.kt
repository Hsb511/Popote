package com.team23.data

import com.team23.data.datasource.DatabaseDriverFactory
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


actual fun platformModule() = module {
    singleOf(::DatabaseDriverFactory)
    factory<HttpClientEngine> { Darwin.create() }
}
