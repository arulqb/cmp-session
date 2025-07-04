package com.codingwitharul.bookmyslot.presentation.ui.pokedex

import com.codingwitharul.bookmyslot.domain.model.Pokemon

data class PokeListUiState(
    var data: List<Pokemon> = emptyList(),
    var error: String = "",
    var loading: Boolean = false
)