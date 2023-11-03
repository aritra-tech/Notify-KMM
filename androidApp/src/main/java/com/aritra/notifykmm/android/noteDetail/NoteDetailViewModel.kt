package com.aritra.notifykmm.android.noteDetail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aritra.notifykmm.domain.note.Note
import com.aritra.notifykmm.domain.note.NoteDataSource
import com.aritra.notifykmm.domain.time.DateTimeUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteDetailViewModel @Inject constructor(
    private val noteDataSource: NoteDataSource,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val title = savedStateHandle.getStateFlow("title", "")
    private val isTitleFocused = savedStateHandle.getStateFlow("isTitleFocused", false)
    private val description = savedStateHandle.getStateFlow("description", "")
    private val isDescriptionFocused = savedStateHandle.getStateFlow("isDescriptionFocused", false)

    val state = combine(
        title,
        isTitleFocused,
        description,
        isDescriptionFocused
    ) { noteTitle, isNoteTitleFocused, noteDescription, isNoteDescriptionFocused ->
        NoteDetailState(
            title = noteTitle,
            isTitleFocused = noteTitle.isEmpty() && !isNoteTitleFocused,
            description = noteDescription,
            isDescriptionFocused = noteDescription.isEmpty() && !isNoteDescriptionFocused
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteDetailState())

    private val _noteBeenSaved = MutableStateFlow(false)
    val noteBeenSaved = _noteBeenSaved.asStateFlow()

    private var existingNoteId: Long? = null

    init {
        savedStateHandle.get<Long>("noteId")?.let {existingNoteId ->

            if (existingNoteId == -1L) {
                return@let
            }

            this.existingNoteId = existingNoteId
            viewModelScope.launch {
                noteDataSource.getNoteById(existingNoteId)?.let { note ->
                    savedStateHandle["title"] = note.title
                    savedStateHandle["description"] = note.description
                }
            }
        }
    }

    fun noteTitleChanged(title: String) {
        savedStateHandle["title"] = title
    }

    fun noteTitleFocusedChanged(isFocused: Boolean) {
        savedStateHandle["isTitleFocused"] = isFocused
    }

    fun noteDescriptionChanged(description: String) {
        savedStateHandle["description"] = description
    }

    fun noteDescriptionFocusedChanged(isFocused: Boolean) {
        savedStateHandle["isDescriptionFocused"] = isFocused
    }

    fun saveNote() {
        viewModelScope.launch {
            noteDataSource.insertNote(
                Note(
                    id = existingNoteId,
                    title = title.value,
                    description = description.value,
                    createdAt = DateTimeUtils.now()
                )
            )
            _noteBeenSaved.value = true
        }
    }
}