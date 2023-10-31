package com.aritra.notifykmm.data.local

import com.aritra.notifykmm.database.NotifyDatabase
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.drivers.native.NativeSqliteDriver

actual class DatabaseDriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(NotifyDatabase.Schema, "note.db")
    }
}