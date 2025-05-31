package com.vansh.noteapp.navigation

sealed class NoteNavigationItem(
    val route: String
) {
    object LoginScreen: NoteNavigationItem("login")
    object SignUpScreen: NoteNavigationItem("SignUp")
    object SplashScreen: NoteNavigationItem("splash")
    object HomeScreen: NoteNavigationItem("home")
    object InsertNoteScreen: NoteNavigationItem("create_notes")
}