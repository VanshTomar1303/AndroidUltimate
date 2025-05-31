package com.vansh.noteapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.vansh.noteapp.events.NoteEvent
import com.vansh.noteapp.events.UserEvent
import com.vansh.noteapp.model.NoteState
import com.vansh.noteapp.model.UserState
import com.vansh.noteapp.screens.InsertNoteScreen
import com.vansh.noteapp.screens.LoginScreen
import com.vansh.noteapp.screens.NoteScreen
import com.vansh.noteapp.screens.SignupScreen
import com.vansh.noteapp.screens.SplashScreen

@Composable
fun NoteNavigation(
    navHostController: NavHostController,
    state: NoteState,
    authState: UserState,
    password: String,
    noteEvent: (NoteEvent) -> Unit,
    authEvent: (UserEvent) -> Unit
) {
    NavHost(
        navController = navHostController,
        startDestination = NoteNavigationItem.SplashScreen.route
    ) {
        composable(NoteNavigationItem.SplashScreen.route) {
            SplashScreen(
                navHostController,
                authState
            )
        }
        composable(NoteNavigationItem.LoginScreen.route) {
            LoginScreen(
                navHostController,
                authState,
                password,
                authEvent
            )
        }
        composable(NoteNavigationItem.SignUpScreen.route) {
            SignupScreen(
                    navHostController,
                    authState,
                    password,
                    authEvent
            )
        }
        composable(NoteNavigationItem.HomeScreen.route) {
            NoteScreen(
                navHostController,
                state,
                authEvent,
                noteEvent
            )
        }
        composable(
            NoteNavigationItem.InsertNoteScreen.route+"/{id}"
        ) {
            val id = it.arguments?.getString("id")
            InsertNoteScreen(navHostController,id,state,noteEvent)
        }
    }
}