package com.vansh.noteapp.model

import com.vansh.noteapp.events.UserEvent

data class UserState(
    val uid: String = "",
    val email: String = "",
    val isAuthenticated: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)