package com.aritra.notifykmm.di

import com.aritra.notifykmm.data.local.DatabaseDriverFactory
import com.aritra.notifykmm.data.note.SqlDelightNoteDataSource
import com.aritra.notifykmm.database.NotifyDatabase
import com.aritra.notifykmm.domain.note.NoteDataSource

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    private val noteDataSource: NoteDataSource by lazy {
        SqlDelightNoteDataSource(NotifyDatabase(factory.createDriver()))
    }
}