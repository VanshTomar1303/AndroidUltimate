package com.vansh.noteapp.events

sealed interface UserEvent {
    data class SetEmail(val email: String): UserEvent
    data class SetPassword(val password: String): UserEvent
    data class Authenticated(val uid: String, val email: String?) : UserEvent
    object UnAuthenticated: UserEvent
    object SignOut: UserEvent
    data class Error(val error: String): UserEvent
    data class LoginRequested(val email: String, val password: String): UserEvent
    data class SignUpRequested(val email: String, val password: String): UserEvent

}