package com.vansh.noteapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vansh.noteapp.model.Note
import com.vansh.noteapp.model.NoteState
import com.vansh.noteapp.model.SortType
import com.vansh.noteapp.model.data.NoteDao
import com.vansh.noteapp.model.data.NoteEvent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@OptIn(ExperimentalCoroutinesApi::class)
class NoteViewmodel(
    private val noteDao: NoteDao
): ViewModel() {
    private val _sortType = MutableStateFlow(SortType.ASC)
    private val _notes = _sortType.flatMapLatest { sortType ->
        when(sortType){
            SortType.ASC -> {
                noteDao.findNoteOrderByAscending()
            }
            SortType.DESC -> {
                noteDao.findNoteOrderByDescending()
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(),emptyList())

    private val _state = MutableStateFlow(NoteState())
    val state = combine(_sortType,_notes,_state) { sortType, notes, state ->
        state.copy(
            notes = notes,
            sortType = sortType
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), NoteState())

    fun onEvent(event: NoteEvent){
        when(event){
            NoteEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingNote = false
                    )
                }
            }
            NoteEvent.SaveNote -> {
                val title = state.value.title
                val content = state.value.content

                if (title.isBlank() || content.isBlank()) return

                val note = Note(
                    title = title,
                    content = content
                )

                viewModelScope.launch {
                    noteDao.saveNote(note)
                }

                _state.update {
                    it.copy(
                        isAddingNote = false,
                        title = "",
                        content = ""
                    )
                }
            }
            is NoteEvent.SetContent -> {
                _state.update {
                    it.copy(
                        content = event.content
                    )
                }
            }
            is NoteEvent.SetTitle -> {
                _state.update {
                    it.copy(
                        title = event.title
                    )
                }
            }
            NoteEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingNote = true
                    )
                }
            }
            is NoteEvent.SortNote -> {
                _sortType.value = event.sortType
            }
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    noteDao.deleteNote(event.note)
                }
            }
        }
    }
}