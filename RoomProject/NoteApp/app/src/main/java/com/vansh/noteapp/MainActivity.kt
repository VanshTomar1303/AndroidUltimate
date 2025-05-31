package com.vansh.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vansh.noteapp.model.data.NoteDatabase
import com.vansh.noteapp.presentation.HomeScreen
import com.vansh.noteapp.ui.theme.NoteAppTheme
import com.vansh.noteapp.viewmodel.NoteViewmodel

class MainActivity : ComponentActivity() {

    private val db by lazy{
        Room.databaseBuilder(
            applicationContext,
            NoteDatabase::class.java, "notes.db"
        ).build()
    }

    private val viewmodel by viewModels<NoteViewmodel>(
        factoryProducer = {
            object : ViewModelProvider.Factory{
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return NoteViewmodel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppTheme {
                val state by viewmodel.state.collectAsState()
                HomeScreen(
                    state,
                    viewmodel::onEvent
                )
            }
        }
    }
}
