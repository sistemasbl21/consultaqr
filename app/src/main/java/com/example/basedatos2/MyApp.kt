package com.example.basedatos2

import android.app.Application
import com.example.basedatos2.di.apiModule
import com.example.basedatos2.di.repositoryModule
import com.example.basedatos2.di.retrofitModule
import com.example.basedatos2.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyApp)
            modules(arrayListOf(apiModule,retrofitModule,repositoryModule,viewModelModule))
        }
    }
}