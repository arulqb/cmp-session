package com.codingwitharul.bookmyslot.di


import com.codingwitharul.bookmyslot.common.DriverFactory
import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.data.networking.ApiClientHelper
import com.codingwitharul.bookmyslot.data.repo.AuthRepoImpl
import com.codingwitharul.bookmyslot.data.repo.BookingRepoImpl
import com.codingwitharul.bookmyslot.data.repo.DiscoverRepoImpl
import com.codingwitharul.bookmyslot.data.repo.PokemonRepositoryImpl
import com.codingwitharul.bookmyslot.db.BookMySlot
import com.codingwitharul.bookmyslot.domain.repo.AuthRepo
import com.codingwitharul.bookmyslot.domain.repo.BookingRepo
import com.codingwitharul.bookmyslot.domain.repo.DiscoverRepo
import com.codingwitharul.bookmyslot.domain.repo.PokemonRepo
import com.codingwitharul.bookmyslot.domain.usecase.GetDiscoveriesUseCase
import com.codingwitharul.bookmyslot.domain.usecase.LoginUseCase
import com.codingwitharul.bookmyslot.presentation.MainViewModel
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking.BookingViewModel
import com.codingwitharul.bookmyslot.presentation.ui.home.screens.discovery.DiscoverViewModel
import com.codingwitharul.bookmyslot.presentation.ui.login.LoginViewModel
import com.codingwitharul.bookmyslot.presentation.ui.pokedex.PokedexViewModel
import com.codingwitharul.bookmyslot.presentation.ui.splash.SplashScreenViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dataModule = module {
    single<DatabaseHelper> {
        DatabaseHelper(BookMySlot(get<DriverFactory>().createDriver()))
    }
    single<PokemonRepo> { PokemonRepositoryImpl(get()) }
    single<AuthRepo> { AuthRepoImpl(get(), get()) }
    single<BookingRepo> { BookingRepoImpl(get(), get()) }
    single<LoginUseCase> { LoginUseCase(get()) }
    single<DiscoverRepo> { DiscoverRepoImpl(get(), get()) }
    singleOf(::GetDiscoveriesUseCase)
}

val domainModule = module {

}
val viewModelModule = module {
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::PokedexViewModel)
    viewModelOf(::BookingViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::DiscoverViewModel)
}

val networkModule = module {
    single<ApiClientHelper> { ApiClientHelper(get()) }
}

expect val platformModule: Module // Platform specific Dependencies

fun appModule() = listOf(networkModule, viewModelModule, dataModule, platformModule)

