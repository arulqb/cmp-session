package com.codingwitharul.bookmyslot.domain.repo

interface NotificationRepo {
    suspend fun sendNotification(userId: String, message: String): Result<Unit>
    suspend fun subscribeToBookingUpdates(userId: String): Result<Unit>
} 