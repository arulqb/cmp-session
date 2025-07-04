package com.codingwitharul.bookmyslot.presentation.ui.pokedex

sealed class PokeUiEvent {
    data object GetPokemonList: PokeUiEvent()
}