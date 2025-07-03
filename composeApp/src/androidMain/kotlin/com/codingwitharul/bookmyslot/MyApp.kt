package com.codingwitharul.bookmyslot

import android.app.Application
import com.codingwitharul.bookmyslot.di.appModule
import com.google.firebase.FirebaseApp

class MyApp: Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        multiplatform.network.cmptoast.AppContext.apply { set(applicationContext) }

    }
}