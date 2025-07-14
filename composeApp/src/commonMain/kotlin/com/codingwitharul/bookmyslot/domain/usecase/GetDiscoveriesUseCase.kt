package com.codingwitharul.bookmyslot.domain.usecase

import com.codingwitharul.bookmyslot.domain.model.DiscoverModel
import com.codingwitharul.bookmyslot.domain.repo.DiscoverRepo

class GetDiscoveriesUseCase(private val repo: DiscoverRepo) {
    suspend operator fun invoke(refresh: Boolean): Result<DiscoverModel> {
        return repo.fetchDiscoverList(refresh)
    }
}