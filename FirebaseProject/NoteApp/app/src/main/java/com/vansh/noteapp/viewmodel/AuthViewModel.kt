package com.vansh.noteapp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.vansh.noteapp.events.UserEvent
import com.vansh.noteapp.model.UserState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AuthViewModel: ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableStateFlow(UserState())
    val authState = _authState.stateIn(
        viewModelScope, SharingStarted.Companion.WhileSubscribed(5000),
        UserState()
    )
    var password: String by mutableStateOf("")
        private set


    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val user = auth.currentUser
        if (user == null) {
            _authState.value = UserState() // unauthenticated default state
        } else {
            _authState.value = UserState(
                uid = user.uid,
                email = user.email ?: "",
                isAuthenticated = true
            )
        }
    }

    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.Authenticated -> {
                _authState.update {
                    it.copy(
                        isAuthenticated = true
                    )
                }
            }
            is UserEvent.Error -> {
                if (_authState.value.email.isBlank()){
                    _authState.update {
                        it.copy(
                            error = "Invalid Credential!"
                        )
                    }
                }
            }
            is UserEvent.SetEmail -> {
                _authState.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is UserEvent.SetPassword -> {
                password = event.password
            }
            UserEvent.UnAuthenticated -> {
                _authState.update {
                    it.copy(
                        isAuthenticated = false
                    )
                }
            }

            is UserEvent.LoginRequested -> {
                val email = event.email
                val password = event.password

                if (email.isBlank() || password.isBlank()) {
                    _authState.update { it.copy(error = "Invalid Credentials!") }
                    return
                }

                _authState.update { it.copy(isLoading = true) }

                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            _authState.update {
                                it.copy(
                                    isAuthenticated = true,
                                    uid = auth.currentUser?.uid.orEmpty(),
                                    email = email
                                )
                            }
                        } else {
                            _authState.update { it.copy(error = task.exception?.message ?: "Login failed.") }
                        }
                    }
            }

            is UserEvent.SignUpRequested -> {

                val email = event.email
                val password = event.password

                if (email.isEmpty() || password.isEmpty()){
                    _authState.update {
                        it.copy(
                            error = "Invalid Credentials!"
                        )
                    }
                    return
                }
                _authState.update {
                    it.copy(
                        isLoading = true
                    )
                }

                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful){
                            _authState.update {
                                it.copy(
                                    isAuthenticated = true,
                                    uid = auth.currentUser?.uid.orEmpty(),
                                    email = email
                                )
                            }
                        }else{
                            _authState.update {
                                it.copy(
                                    error = "Invalid Credentials!"
                                )
                            }
                        }
                    }
            }

            UserEvent.SignOut -> {
                auth.signOut()
                _authState.update {
                    it.copy(
                        isAuthenticated = false
                    )
                }
            }
        }
    }
}