package com.vansh.noteapp.model

data class NoteState(
    val notes: List<Note> = emptyList(),
    val title: String = "",
    val content: String = "",
    val isAddingNote: Boolean = false,
    val sortType: SortType = SortType.ASC
)