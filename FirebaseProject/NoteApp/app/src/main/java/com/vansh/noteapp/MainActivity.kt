package com.vansh.noteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.vansh.noteapp.navigation.NoteNavigation
import com.vansh.noteapp.viewmodel.AuthViewModel
import com.vansh.noteapp.viewmodel.NoteViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navHostController = rememberNavController()

            val noteViewModel: NoteViewModel = viewModel()

            val authViewModel: AuthViewModel = viewModel()

            val noteState by noteViewModel.state.collectAsState()
            val authState by authViewModel.authState.collectAsState()
            val password = authViewModel.password

            NoteNavigation(navHostController,noteState,authState,password,noteViewModel::onEvent,authViewModel::onEvent)
        }
    }
}
