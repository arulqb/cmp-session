package com.codingwitharul.bookmyslot.domain.repo

interface FeedbackRepo {
    suspend fun submitRating(bookingId: String, rating: Int, review: String): Result<Unit>
    suspend fun reportIssue(bookingId: String, issue: String): Result<Unit>
} 