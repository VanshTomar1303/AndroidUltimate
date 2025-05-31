package com.vansh.noteapp.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vansh.noteapp.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class NoteDatabase: RoomDatabase() {
    abstract val dao: NoteDao
}