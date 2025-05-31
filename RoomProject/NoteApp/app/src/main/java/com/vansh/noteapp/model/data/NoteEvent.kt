package com.vansh.noteapp.model.data

import com.vansh.noteapp.model.Note
import com.vansh.noteapp.model.SortType

sealed interface NoteEvent{
    object SaveNote: NoteEvent
    data class SetTitle(val title: String): NoteEvent
    data class SetContent(val content: String): NoteEvent
    data class SortNote(val sortType: SortType): NoteEvent
    data class DeleteNote(val note: Note): NoteEvent
    object ShowDialog: NoteEvent
    object HideDialog: NoteEvent
}