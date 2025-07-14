package com.codingwitharul.bookmyslot.presentation.ui.home.screens.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codingwitharul.bookmyslot.domain.model.Booking
import com.codingwitharul.bookmyslot.domain.repo.BookingRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

class BookingViewModel(private val bookingRepo: BookingRepo) : ViewModel() {
    private val _uiState = MutableStateFlow(BookingUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Load available dates (stub)
        val now = Clock.System.now()
        val availableDates = (0..14).map { now.plus(1.days).toLocalDateTime(TimeZone.currentSystemDefault()) }
        _uiState.value = _uiState.value.copy(availableDates = availableDates)
    }

    fun onEvent(event: BookingUiEvent) {
        when (event) {
            is BookingUiEvent.LoadBookings -> {
                loadBookings()
            }
            is BookingUiEvent.CreateBooking -> {
                createBooking(event.booking)
            }
            is BookingUiEvent.UpdateBooking -> {
                updateBooking(event.booking)
            }
            is BookingUiEvent.CancelBooking -> {
                cancelBooking(event.bookingId)
            }
            is BookingUiEvent.SelectBooking -> {
                _uiState.value = _uiState.value.copy(selectedBooking = event.booking)
            }
            is BookingUiEvent.ClearSelection -> {
                _uiState.value = _uiState.value.copy(selectedBooking = null)
            }
            is BookingUiEvent.SelectDate -> {
                _uiState.value = _uiState.value.copy(selectedDate = event.date)
                onEvent(BookingUiEvent.LoadAvailableSlots(event.date))
            }
            is BookingUiEvent.LoadAvailableSlots -> {
                // Stub: generate slots for the selected date
                val slots = listOf("09:00 AM", "10:00 AM", "11:00 AM", "01:00 PM", "03:00 PM", "05:00 PM")
                _uiState.value = _uiState.value.copy(availableTimeSlots = slots, selectedTimeSlot = null)
            }
            is BookingUiEvent.SelectTimeSlot -> {
                _uiState.value = _uiState.value.copy(selectedTimeSlot = event.timeSlot)
            }
            is BookingUiEvent.BookSelectedSlot -> {
                // TODO: Implement booking logic
                // For now, just clear selection
                _uiState.value = _uiState.value.copy(selectedTimeSlot = null)
            }
        }
    }

    private fun loadBookings() {
        _uiState.value = _uiState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            try {
                val bookings = bookingRepo.getBookings("") // TODO: Pass userId
                _uiState.value = _uiState.value.copy(bookings = bookings, loading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(loading = false, error = e.message ?: "Error loading bookings")
            }
        }
    }

    private fun createBooking(booking: Booking) {
        _uiState.value = _uiState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            val result = bookingRepo.createBooking(booking)
            if (result.isSuccess) {
                onEvent(BookingUiEvent.LoadBookings)
            } else {
                _uiState.value = _uiState.value.copy(loading = false, error = result.exceptionOrNull()?.message ?: "Error creating booking")
            }
        }
    }

    private fun updateBooking(booking: Booking) {
        _uiState.value = _uiState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            val result = bookingRepo.updateBooking(booking)
            if (result.isSuccess) {
                onEvent(BookingUiEvent.LoadBookings)
            } else {
                _uiState.value = _uiState.value.copy(loading = false, error = result.exceptionOrNull()?.message ?: "Error updating booking")
            }
        }
    }

    private fun cancelBooking(bookingId: String) {
        _uiState.value = _uiState.value.copy(loading = true, error = "")
        viewModelScope.launch {
            val result = bookingRepo.cancelBooking(bookingId)
            if (result.isSuccess) {
                onEvent(BookingUiEvent.LoadBookings)
            } else {
                _uiState.value = _uiState.value.copy(loading = false, error = result.exceptionOrNull()?.message ?: "Error cancelling booking")
            }
        }
    }
} 