package com.vansh.noteapp.model.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.vansh.noteapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Upsert
    suspend fun saveNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("Select * from note Order by title ASC")
    fun findNoteOrderByAscending(): Flow<List<Note>>

    @Query("Select * from note Order by title DESC")
    fun findNoteOrderByDescending(): Flow<List<Note>>
}