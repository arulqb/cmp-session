package com.codingwitharul.bookmyslot.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.codingwitharul.bookmyslot.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookMySlotDatabase(val database: BookMySlot) {

    fun getStatus(): Flow<List<Status>> =
        database.statusQueries.getAllMessages().asFlow()
            .mapToList(Dispatchers.Unconfined)
            .map { list ->
                list.map { it }
            }

    fun savePokemon(list: List<Pokemon>) {
        database.transaction {
            list.forEach { pokemon ->
                database.pokemonQueries.savePokemon(pokemon.name, pokemon.url)
            }
        }
    }

}