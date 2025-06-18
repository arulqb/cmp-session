package com.codingwitharul.bookmyslot.presentation.pokedex

sealed class PokeUiEvent {
    data object GetPokemonList: PokeUiEvent()
}