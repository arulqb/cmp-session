package com.codingwitharul.bookmyslot

import android.app.Application
import com.codingwitharul.bookmyslot.di.appModule

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }

    }
}