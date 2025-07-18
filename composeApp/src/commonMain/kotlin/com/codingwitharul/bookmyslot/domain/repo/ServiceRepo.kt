package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.domain.model.DiscoverModel

interface ServiceRepo {
    suspend fun getServices(): List<DiscoverModel>
} 