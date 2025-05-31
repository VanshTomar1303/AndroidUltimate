package com.vansh.noteapp.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.vansh.noteapp.events.NoteEvent
import com.vansh.noteapp.model.Note
import com.vansh.noteapp.model.NoteState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class NoteViewModel() : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    private val currentUserUid = auth.currentUser?.uid
    private val notesDBReference = currentUserUid?.let {
        db.collection("users").document(it).collection("notes")
    }

    private val _state = MutableStateFlow(NoteState(dataLoading = true))
    val state = _state.stateIn(
        viewModelScope, SharingStarted.WhileSubscribed(5000),
        NoteState()
    )
    private val snapshotListener =
        EventListener<QuerySnapshot> { snapshot, e -> handleSnapshot(snapshot, e) }

    init {
        notesDBReference?.addSnapshotListener(snapshotListener)
    }

    private fun handleSnapshot(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
        if (error == null) {
            val noteList = value?.toObjects(Note::class.java) ?: emptyList()
            _state.update {
                it.copy(
                    dataLoading = false,
                    notes = noteList,
                    error = null
                )
            }
        } else {
            _state.update {
                it.copy(dataLoading = false, error = error.message)
            }
        }
    }

    fun onEvent(event: NoteEvent) {
        when (event) {
            is NoteEvent.DeleteNote -> {
                viewModelScope.launch {
                    _state.update { it.copy(dataLoading = true) }
                    try {
                        notesDBReference?.document(event.id)?.delete()?.await()
                        _state.update { it.copy(dataLoading = false) }
                    } catch (e: Exception) {
                        _state.update { it.copy(dataLoading = false, error = e.message) }
                    }
                }
            }
            NoteEvent.Expended -> {
                _state.update {
                    it.copy(expended = true)
                }
            }

            NoteEvent.UnExpended -> {
                _state.update {
                    it.copy(expended = false)
                }
            }

            NoteEvent.SaveNote -> {
                viewModelScope.launch {
                    val title = state.value.title.trim()
                    val description = state.value.description.trim()

                    if (title.isEmpty() && description.isEmpty()) {
                        // No empty note to save
                        return@launch
                    }

                    _state.update { it.copy(dataLoading = true) }

                    val id = notesDBReference?.document()?.id ?: run {
                        _state.update { it.copy(dataLoading = false, error = "User not logged in.") }
                        return@launch
                    }

                    val note = Note(id = id, title = title, description = description)

                    try {
                        notesDBReference.document(id).set(note).await()
                        _state.update {
                            it.copy(
                                dataLoading = false,
                                title = "",
                                description = "",
                                expended = false,
                                noteSaved = true
                            )
                        }

                    } catch (e: Exception) {
                        _state.update { it.copy(dataLoading = false, error = e.message) }

                    }
                }
            }

            is NoteEvent.SetDescription -> {
                _state.update { it.copy(description = event.description) }
            }

            is NoteEvent.SetTitle -> {
                _state.update { it.copy(title = event.title) }
            }

            is NoteEvent.UpdateNote -> {
                viewModelScope.launch {
                    val title = state.value.title.trim()
                    val description = state.value.description.trim()

                    if (title.isEmpty() && description.isEmpty()) {
                        // No empty note to update
                        return@launch
                    }

                    _state.update { it.copy(dataLoading = true) }

                    val id = event.id
                    val note = Note(id = id, title = title, description = description)

                    try {
                        notesDBReference?.document(id)?.set(note)?.await()
                        _state.update {
                            it.copy(
                                dataLoading = false,
                                title = "",
                                description = "",
                                expended = false
                            )
                        }
                    } catch (e: Exception) {
                        _state.update { it.copy(dataLoading = false, error = e.message) }
                    }
                }
            }

            is NoteEvent.UpdateNoteDataLoading -> {
                viewModelScope.launch {
                    _state.update { it.copy(dataLoading = true) }
                    try {
                        val rawData = notesDBReference?.document(event.id)?.get()?.await()
                        val data = rawData?.toObject(Note::class.java)
                        _state.update {
                            it.copy(
                                title = data?.title ?: "",
                                description = data?.description ?: "",
                                dataLoading = false
                            )
                        }
                    } catch (e: Exception) {
                        _state.update { it.copy(dataLoading = false, error = e.message) }
                    }
                }
            }
        }
    }
}
