package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.domain.model.Service

interface ServiceRepo {
    suspend fun getServices(): List<Service>
} 