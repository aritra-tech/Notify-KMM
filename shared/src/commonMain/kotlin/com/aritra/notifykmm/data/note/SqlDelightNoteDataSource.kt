package com.aritra.notifykmm.data.note

import com.aritra.notifykmm.database.NotifyDatabase
import com.aritra.notifykmm.domain.note.Note
import com.aritra.notifykmm.domain.note.NoteDataSource
import com.aritra.notifykmm.domain.time.DateTimeUtils

class SqlDelightNoteDataSource(db: NotifyDatabase) : NoteDataSource  {

    private val queries = db.noteQueries

    override suspend fun getAllNotes(): List<Note> {
        return queries
            .getAllNotes()
            .executeAsList()
            .map { it.toNote() }
    }

    override suspend fun getNoteById(id: Long): Note? {
        return queries
            .getNoteById(id)
            .executeAsOneOrNull()
            ?.toNote()
    }

    override suspend fun insertNote(note: Note) {
        queries.insertNote(
            id = note.id,
            title = note.title,
            content = note.description,
            created = DateTimeUtils.toEpochMills(note.createdAt)
        )
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNoteById(id)
    }
}