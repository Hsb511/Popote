package com.team23.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val dataModule = module {
    factory { CIO.create() }
    factory { HttpClientFactory(get()) }
    single { provideHttpClient(get()) }
}

private fun provideHttpClient(
    httpClientFactory: HttpClientFactory,
): HttpClient = httpClientFactory.createClient(
    baseUrl = "https://neuracr.github.io/",
    isDebug = true,
)
