package com.codingwitharul.bookmyslot.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    val name: String,
    val url: String
)

@Serializable
data class PokemonResponse(
    val count: Long,
    val prev: String?,
    val next: String?,
    val results: List<Pokemon> = listOf()
)