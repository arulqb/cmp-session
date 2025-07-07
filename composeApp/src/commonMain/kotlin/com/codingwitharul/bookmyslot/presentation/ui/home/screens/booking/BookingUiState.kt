package com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking

import com.codingwitharul.bookmyslot.domain.model.Booking
import kotlinx.datetime.LocalDateTime

data class BookingUiState(
    val bookings: List<Booking> = emptyList(),
    val loading: Boolean = false,
    val error: String = "",
    val selectedBooking: Booking? = null,
    val availableDates: List<LocalDateTime> = emptyList(),
    val selectedDate: LocalDateTime? = null,
    val availableTimeSlots: List<String> = emptyList(),
    val selectedTimeSlot: String? = null
) 