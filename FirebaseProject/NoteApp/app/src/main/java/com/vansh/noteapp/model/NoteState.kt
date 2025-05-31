package com.vansh.noteapp.model

data class NoteState(
    val notes: List<Note> = emptyList(),
    val id: String = "",
    val title: String = "",
    val description: String = "",
    val expended: Boolean = false,
    val dataLoading: Boolean = false,
    val noteSaved: Boolean = false,
    val noteUpdated: Boolean = false,
    val error: String? = null
)