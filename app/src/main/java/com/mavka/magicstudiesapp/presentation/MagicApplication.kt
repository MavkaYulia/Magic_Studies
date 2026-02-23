package com.mavka.magicstudiesapp.presentation

import android.app.Application
import com.mavka.magicstudiesapp.koin.module.dataModule
import com.mavka.magicstudiesapp.koin.module.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MagicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MagicApplication)
            modules(dataModule, uiModule)
        }
    }
}
