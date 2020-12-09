package com.ydh.photo

import android.app.Application
import com.ydh.photo.di.apiModule
import com.ydh.photo.di.networkModule
import com.ydh.photo.di.repositoryModule
import com.ydh.photo.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PhotoApp: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PhotoApp)
            modules(
                apiModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}