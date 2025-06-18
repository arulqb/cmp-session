package com.codingwitharul.bookmyslot.common

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.codingwitharul.bookmyslot.db.BookMySlot

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(BookMySlot.Schema, context, BMSDbName)
    }
}