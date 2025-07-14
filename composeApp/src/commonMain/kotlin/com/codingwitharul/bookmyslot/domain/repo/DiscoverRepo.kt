package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.domain.model.DiscoverModel

interface DiscoverRepo {

    suspend fun fetchDiscoverList(refresh: Boolean = false): Result<DiscoverModel>

}