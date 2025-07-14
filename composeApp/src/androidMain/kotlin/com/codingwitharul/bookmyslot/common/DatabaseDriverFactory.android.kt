package com.codingwitharul.bookmyslot.common

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.codingwitharul.bookmyslot.db.BookMySlot

actual class DriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            BookMySlot.Schema,
            context,
            BMSDbName,
            callback = object : AndroidSqliteDriver.Callback(BookMySlot.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            })
    }
}