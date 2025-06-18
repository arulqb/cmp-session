package com.codingwitharul.bookmyslot.common

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.db.SqlSchema
import com.codingwitharul.bookmyslot.db.BookMySlot


expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): BookMySlot {
    val driver = driverFactory.createDriver()
    val database = BookMySlot(driver)

    return database
}


const val BMSDbName = "bookmyslot.db"