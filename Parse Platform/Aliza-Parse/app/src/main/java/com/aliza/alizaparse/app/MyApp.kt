package com.aliza.alizaparse.app

import android.app.Application
import com.aliza.alizaparse.R
import com.parse.Parse

class MyApp:Application() {
    override fun onCreate() {
        super.onCreate()

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