package com.vansh.noteapp.events

sealed interface NoteEvent{
    data class UpdateNote(val id: String): NoteEvent
    data class SetTitle(val title: String): NoteEvent
    data class SetDescription(val description: String): NoteEvent
    object Expended: NoteEvent
    object UnExpended: NoteEvent
    data class DeleteNote(val id: String): NoteEvent
    object SaveNote: NoteEvent
    data class UpdateNoteDataLoading(val id: String): NoteEvent
}