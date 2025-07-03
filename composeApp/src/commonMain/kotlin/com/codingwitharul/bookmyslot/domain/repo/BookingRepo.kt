package com.codingwitharul.bookmyslot.domain.repo

import com.codingwitharul.bookmyslot.domain.model.Booking

interface BookingRepo {
    suspend fun getBookings(userId: String): List<Booking>
    suspend fun createBooking(booking: Booking): Result<Booking>
    suspend fun updateBooking(booking: Booking): Result<Booking>
    suspend fun cancelBooking(bookingId: String): Result<Unit>
} 