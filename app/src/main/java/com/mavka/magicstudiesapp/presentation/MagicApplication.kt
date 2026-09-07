package com.mavka.magicstudiesapp.presentation

import android.app.Application
import com.mavka.magicstudiesapp.koin.module.dataModule
import com.mavka.magicstudiesapp.koin.module.uiModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MagicApplication : Application() {

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MagicApplication)
            modules(dataModule(applicationScope), uiModule)
        }
    }
}
