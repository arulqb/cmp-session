package com.codingwitharul.bookmyslot.di

import com.codingwitharul.bookmyslot.common.DriverFactory
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<DriverFactory> { DriverFactory() }
}