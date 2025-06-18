package com.codingwitharul.bookmyslot.di


import com.codingwitharul.bookmyslot.common.DriverFactory
import com.codingwitharul.bookmyslot.data.PokemonRepositoryImpl
import com.codingwitharul.bookmyslot.db.BookMySlot
import com.codingwitharul.bookmyslot.db.BookMySlotDatabase
import com.codingwitharul.bookmyslot.domain.repo.PokemonRepo
import com.codingwitharul.bookmyslot.presentation.pokedex.PokedexViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val dataModule = module {
    single<BookMySlotDatabase> {
        BookMySlotDatabase(BookMySlot(get<DriverFactory>().createDriver()))
    }
    single<PokemonRepo> { PokemonRepositoryImpl(get()) }

    viewModelOf(::PokedexViewModel)
}

expect val platformModule: Module // Platform specific Dependencies

fun appModule() = listOf(dataModule, platformModule)

