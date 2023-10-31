package com.aritra.notifykmm.data.local

import android.content.Context
import com.aritra.notifykmm.database.NotifyDatabase
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(NotifyDatabase.Schema, context, "note.db")
    }
}