package com.codingwitharul.bookmyslot.data.repo


import com.codingwitharul.bookmyslot.common.client
import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.domain.model.Pokemon
import com.codingwitharul.bookmyslot.domain.model.PokemonResponse
import com.codingwitharul.bookmyslot.domain.repo.PokemonRepo
import com.codingwitharul.bookmyslot.utils.Resource
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PokemonRepositoryImpl(val db: DatabaseHelper): PokemonRepo {
    override suspend fun getPokemonList(): Flow<Resource<PokemonResponse>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                val response = client.get{
                    url("pokemon")
                }
                emit(Resource.Success(response.body()))
            }catch (e : RedirectResponseException){
                // handle 3xx codes
                emit(Resource.Error(e.response.status.description))

            }catch (e: ClientRequestException){
                //handle 4xx error codes
                emit(Resource.Error(e.response.status.description))

            }catch (e : ServerResponseException){
                //handle 5xx error codes
                emit(Resource.Error(e.response.status.description))
            }catch (e: Exception){
                emit(Resource.Error(e.message?:"Something went wrong"))
            }
        }
    }

    override suspend fun savePokemon(pokemon: List<Pokemon>) {
        db.savePokemon(pokemon)
    }
}