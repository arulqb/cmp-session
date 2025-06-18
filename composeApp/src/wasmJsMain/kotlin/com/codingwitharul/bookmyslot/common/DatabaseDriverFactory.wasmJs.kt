package com.codingwitharul.bookmyslot.common

import app.cash.sqldelight.db.SqlDriver
import com.codingwitharul.bookmyslot.db.BookMySlot
import org.w3c.dom.Worker

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
//        Worker
//        return WebWorkerDriver(
//            Worker(
//                js("""new URL("@cashapp/sqldelight-sqljs-worker/sqljs.worker.js", import.meta.url)""")
//            )
//        ).also { schema.create(it).await() }
        TODO()
    }
}