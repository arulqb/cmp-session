package com.codingwitharul.bookmyslot.data.repo

import com.codingwitharul.bookmyslot.data.db.DatabaseHelper
import com.codingwitharul.bookmyslot.data.networking.ApiClientHelper
import com.codingwitharul.bookmyslot.domain.model.Booking
import com.codingwitharul.bookmyslot.domain.repo.BookingRepo

class BookingRepoImpl(val db: DatabaseHelper, val apiClientHelper: ApiClientHelper): BookingRepo {
    override suspend fun getBookings(userId: String): List<Booking> {
        TODO("Not yet implemented")
    }

    override suspend fun createBooking(booking: Booking): Result<Booking> {
        TODO("Not yet implemented")
    }

    override suspend fun updateBooking(booking: Booking): Result<Booking> {
        TODO("Not yet implemented")
    }

    override suspend fun cancelBooking(bookingId: String): Result<Unit> {
        TODO("Not yet implemented")
    }
}