package com.codingwitharul.bookmyslot.di

import com.codingwitharul.bookmyslot.common.DriverFactory
import com.codingwitharul.bookmyslot.common.auth.GoogleAuthProvider
import org.koin.core.module.Module
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DriverFactory> { DriverFactory() }
    factoryOf(::GoogleAuthProvider) bind GoogleAuthProvider::class
}