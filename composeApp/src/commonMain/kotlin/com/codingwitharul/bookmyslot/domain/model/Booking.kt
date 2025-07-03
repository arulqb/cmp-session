package com.codingwitharul.bookmyslot.domain.model

import java.time.LocalDate

data class Booking(
    val id: String,
    val service: Service,
    val date: LocalDate,
    val timeSlot: String,
    val notes: String,
    val status: BookingStatus
)

enum class BookingStatus { PENDING, CONFIRMED, IN_PROGRESS, COMPLETED, CANCELLED } 