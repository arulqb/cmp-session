package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.domain.model.Pokemon
import com.codingwitharul.bookmyslot.domain.model.PokemonResponse
import com.codingwitharul.bookmyslot.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PokemonRepo {
    suspend fun getPokemonList(): Flow<Resource<PokemonResponse>>
    suspend fun savePokemon(pokemon: List<Pokemon>)
}