package com.vansh.backstack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vansh.backstack.ui.theme.BackStackTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackStackTheme {

            }
        }
    }
}

/*
    BackStack -> Stack of screen or activity.

    Task -> Collection of multiple screen

    Standard (default) - Each time the Activity is launched, a new instance is created.
    SingleTop - If the Activity is already at the top, it is not recreated.
    SingleTask - If the Activity exists in the stack, it is brought to the front, and all above it are cleared.
    SingleInstance - The Activity is the only one in its task; any other activities are launched in a separate task.
 */
