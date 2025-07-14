package com.codingwitharul.bookmyslot.presentation.ui.home.screens.discovery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwitharul.bookmyslot.domain.usecase.GetDiscoveriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class DiscoverViewModel(private val getDiscovery: GetDiscoveriesUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(DiscoverState())
    val uiState: StateFlow<DiscoverState> = _uiState

    fun onEvent(event: DiscoverUIEvent) {
        when (event) {
            is DiscoverUIEvent.OnSearchQueryChange -> _uiState.value =
                _uiState.value.copy(searchQuery = event.query)

            DiscoverUIEvent.OnSearch -> { /* TODO: Implement search logic */
            }

            is DiscoverUIEvent.OnCategorySelected -> _uiState.value =
                _uiState.value.copy(selectedCategory = event.category)

            is DiscoverUIEvent.OnLoadDiscovery -> getDiscoveries()
        }
    }

    private fun getDiscoveries() {
        viewModelScope.launch {
            val dis = getDiscovery(false)
            if (dis.isSuccess) {
                _uiState.value =
                    _uiState.value.copy(discoveries = dis.getOrNull(), isLoading = false)
            } else {
                _uiState.value =
                    _uiState.value.copy(error = dis.exceptionOrNull().toString(), isLoading = false)
            }
        }
    }
}