package com.vansh.noteapp.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.vansh.noteapp.events.UserEvent
import com.vansh.noteapp.model.UserState
import com.vansh.noteapp.navigation.NoteNavigationItem
import com.vansh.noteapp.screens.common.MyOutlinedTextfield
import com.vansh.noteapp.ui.theme.colorBlack
import com.vansh.noteapp.ui.theme.colorLightBlack
import com.vansh.noteapp.ui.theme.colorRed
import com.vansh.noteapp.ui.theme.colorWhite

@Composable
fun SignupScreen(
    navHostController: NavHostController,
    authState: UserState,
    password: String,
    authEvent: (UserEvent) -> Unit
) {
    val context = LocalContext.current
    LaunchedEffect(authState.isAuthenticated) {
        if(authState.isAuthenticated){
            navHostController.navigate(NoteNavigationItem.HomeScreen.route) {
                popUpTo(NoteNavigationItem.SignUpScreen.route) {
                    inclusive = true // Optional: prevents back-navigation to login
                }
            }
        }
    }
    LaunchedEffect(authState.error) {
        authState.error?.let {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }


    Column(
        modifier = Modifier.fillMaxSize()
            .background(colorBlack),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Note App Signup",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = colorRed
        )
        Spacer(modifier = Modifier.height(30.dp))
        MyOutlinedTextfield(
            state = authState.email,
            onValueChange = {
                authEvent(UserEvent.SetEmail(email = it))
            },
            icon = Icons.Rounded.Email,
            name = "Email"
        )
        MyOutlinedTextfield(
            state = password,
            onValueChange = {
                authEvent(UserEvent.SetPassword(it))
            },
            icon = Icons.Rounded.Lock,
            name = "Password"
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {
                authEvent(UserEvent.SignUpRequested(authState.email,password))
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorRed,
                contentColor = colorLightBlack
            )
        ) {
            Text(
                text = "Sign Up",
                fontSize = 20.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Already have an account.",
                color = colorWhite
            )
            TextButton(
                onClick = {
                    navHostController.navigate(NoteNavigationItem.LoginScreen.route)
                }
            ) {
                Text(
                    text = "Login.",
                    color = colorRed
                )
            }
        }
    }
}