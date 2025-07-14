package com.codingwitharul.bookmyslot.domain.model

import kotlinx.datetime.LocalDateTime

data class Booking(
    val id: String,
    val service: DiscoverModel,
    val date: LocalDateTime,
    val timeSlot: String,
    val notes: String,
    val status: BookingStatus
)

enum class BookingStatus { PENDING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED } 