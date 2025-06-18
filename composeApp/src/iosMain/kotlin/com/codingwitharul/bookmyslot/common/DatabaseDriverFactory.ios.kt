package com.codingwitharul.bookmyslot.common

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.codingwitharul.bookmyslot.db.BookMySlot

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(BookMySlot.Schema, BMSDbName)
    }
}