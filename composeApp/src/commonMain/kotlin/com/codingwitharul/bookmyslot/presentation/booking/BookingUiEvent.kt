package com.codingwitharul.bookmyslot.presentation.booking

import com.codingwitharul.bookmyslot.domain.model.Booking
import kotlinx.datetime.LocalDateTime

sealed class BookingUiEvent {
    object LoadBookings : BookingUiEvent()
    data class CreateBooking(val booking: Booking) : BookingUiEvent()
    data class UpdateBooking(val booking: Booking) : BookingUiEvent()
    data class CancelBooking(val bookingId: String) : BookingUiEvent()
    data class SelectBooking(val booking: Booking) : BookingUiEvent()
    object ClearSelection : BookingUiEvent()
    data class SelectDate(val date: LocalDateTime) : BookingUiEvent()
    data class LoadAvailableSlots(val date: LocalDateTime) : BookingUiEvent()
    data class SelectTimeSlot(val timeSlot: String) : BookingUiEvent()
    object BookSelectedSlot : BookingUiEvent()
} 