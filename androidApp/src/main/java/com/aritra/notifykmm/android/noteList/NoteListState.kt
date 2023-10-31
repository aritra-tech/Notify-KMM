package com.aritra.notifykmm.android.noteList

import com.aritra.notifykmm.domain.note.Note

data class NoteListState(
    val notes: List<Note> = emptyList(),
    val searchText: String = "",
    val isSearchActive: Boolean = false
)