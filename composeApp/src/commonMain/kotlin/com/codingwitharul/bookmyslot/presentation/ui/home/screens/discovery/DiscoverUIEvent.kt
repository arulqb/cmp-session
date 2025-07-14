package com.codingwitharul.bookmyslot.presentation.ui.home.screens.discovery

sealed class DiscoverUIEvent {
    data class OnLoadDiscovery(val forceRefresh: Boolean = false) : DiscoverUIEvent()
    data class OnSearchQueryChange(val query: String) : DiscoverUIEvent()
    object OnSearch : DiscoverUIEvent()
    data class OnCategorySelected(val category: String) : DiscoverUIEvent()
}
