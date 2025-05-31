package com.vansh.firebaseauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.vansh.firebaseauthapp.ui.theme.FirebaseAuthAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authViewModel: AuthViewModel by viewModels()
        setContent {
            FirebaseAuthAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    MyAppNavigation(
                        modifier = Modifier.padding(it),
                        authViewModel = authViewModel
                    )
                }
            }
        }
    }
}
