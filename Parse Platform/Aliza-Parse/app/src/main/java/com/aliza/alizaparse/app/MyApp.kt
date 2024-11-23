package com.aliza.alizaparse.app

import android.app.Application
import com.aliza.alizaparse.R
import com.aliza.alizaparse.di.appModule
import com.parse.Parse
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }

        // parse backend init =>
        Parse.initialize(
            Parse.Configuration.Builder(applicationContext)
                .server(getString(R.string.back4app_server_url))
                .clientKey(getString(R.string.back4app_client_key))
                .applicationId(getString(R.string.back4app_app_id))
                .build()
        )
    }
}