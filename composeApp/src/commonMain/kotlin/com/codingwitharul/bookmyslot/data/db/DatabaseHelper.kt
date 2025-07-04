package com.codingwitharul.bookmyslot.data.db

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.codingwitharul.bookmyslot.db.BookMySlot
import com.codingwitharul.bookmyslot.db.Status
import com.codingwitharul.bookmyslot.db.UserInfo
import com.codingwitharul.bookmyslot.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DatabaseHelper(val database: BookMySlot) {

    private val userInfoQueries = database.userInfoQueries
    private val statusQueries = database.statusQueries

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

    fun saveUserInfo(user: UserInfo) {
        userInfoQueries.transaction {
            userInfoQueries.insertUser(
                userId = user.userId,
                userName = user.userName,
                email = user.email,
                phoneNumber = user.phoneNumber,
                photoUri = user.photoUri,
                authToken = user.authToken,
                providerId = user.providerId,
                displayName = user.displayName,
                bio = user.bio,
                dateOfBirth = user.dateOfBirth,
                lastLoginAt = user.lastLoginAt,
                isLoggedIn = true
            )
        }
    }

    fun getActiveUserInfo():  UserInfo? {
        return userInfoQueries.getLoggedInUser().executeAsOneOrNull()
    }
}