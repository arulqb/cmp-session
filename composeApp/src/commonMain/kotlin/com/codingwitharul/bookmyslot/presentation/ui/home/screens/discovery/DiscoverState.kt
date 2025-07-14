package com.codingwitharul.bookmyslot.presentation.ui.home.screens.discovery

import com.codingwitharul.bookmyslot.domain.model.DiscoverModel

data class DiscoverState(
    val searchQuery: String = "",
    val selectedCategory: String = "All",
    val discoveries: DiscoverModel? = null, // Replace Any with your Discovery model
    val isLoading: Boolean = false,
    val error: String? = null
)