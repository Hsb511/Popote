package com.team23.neuracrsrecipes.android

import android.app.Application
import com.team23.domain.domainModule
import com.team23.neuracrsrecipes.presentationModule
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        androidAppInit()
    }

    private fun androidAppInit() {
        startKoin {
            modules(
                listOf(
                    domainModule,
                    presentationModule,
                )
            )
        }
    }
}
