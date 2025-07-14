package com.codingwitharul.bookmyslot.domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.Serializable

@Serializable
data class BannerItem(
    val image: String, val title: String, val createdAt: LocalDateTime
)