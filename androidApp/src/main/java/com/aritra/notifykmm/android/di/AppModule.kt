package com.aritra.notifykmm.android.di

import android.app.Application
import com.aritra.notifykmm.data.local.DatabaseDriverFactory
import com.aritra.notifykmm.data.note.SqlDelightNoteDataSource
import com.aritra.notifykmm.database.NotifyDatabase
import com.aritra.notifykmm.domain.note.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSQLDriver(app: Application) : SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(driver:SqlDriver) : NoteDataSource {
        return SqlDelightNoteDataSource(NotifyDatabase(driver))
    }
}