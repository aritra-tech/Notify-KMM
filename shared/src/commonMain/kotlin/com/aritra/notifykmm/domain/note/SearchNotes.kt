package com.aritra.notifykmm.domain.note

import com.aritra.notifykmm.domain.time.DateTimeUtils

class SearchNotes {
    fun execute(notes: List<Note>, query: String): List<Note> {
        if(query.isBlank()) {
            return notes
        }
        return notes.filter {
            it.title.trim().lowercase().contains(query.lowercase())

        }.sortedBy {
            DateTimeUtils.toEpochMills(it.createdAt)
        }
    }
}