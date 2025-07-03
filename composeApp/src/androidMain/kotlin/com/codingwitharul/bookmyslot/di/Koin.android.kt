package com.codingwitharul.bookmyslot.di

import com.codingwitharul.bookmyslot.common.DriverFactory
import com.codingwitharul.bookmyslot.common.firebase.AuthService
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DriverFactory> { DriverFactory(androidContext()) } // Provide Android Context
    single<AuthService> { AuthService() }
}