package com.team23.neuracrsrecipes

import android.app.Application
import com.team23.data.dataModule
import com.team23.domain.domainModule
import com.team23.view.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        androidAppInit()
    }

    private fun androidAppInit() {
        startKoin {
            androidContext(this@AndroidApplication)
            modules(
                listOf(
                    dataModule,
                    domainModule,
                    presentationModule,
                    viewModule,
                )
            )
        }
    }
}
