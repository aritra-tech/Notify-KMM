package com.aritra.notifykmm.domain.note

import kotlinx.datetime.LocalDateTime

data class Note(
    var id: Long?,
    var title: String,
    var description: String,
    var createdAt: LocalDateTime
)
