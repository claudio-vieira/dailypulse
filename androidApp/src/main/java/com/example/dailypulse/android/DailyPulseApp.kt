package com.example.dailypulse.android

import android.app.Application
import com.example.dailypulse.android.di.viewModelsModule
import com.example.dailypulse.di.sharedKoinModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class DailyPulseApp: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelsModule

        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}