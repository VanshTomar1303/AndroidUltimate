package com.vansh.activity

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
import com.vansh.activity.ui.theme.ActivityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ActivityTheme {

            }
        }
    }
}

/*
    Activity -> it is a unit of app where user interact with the app.
    '-> Entry point of a app.

onCreate():
Called when the activity is first created.
Ideal for initializing components (views, data binding, etc.).

onStart():
Called when the activity is becoming visible to the user.
The app is not yet interactive.

onResume():
Called when the activity starts interacting with the user.
The activity is now at the foreground and the user can interact with it.

onPause():
Called when the system is about to resume another activity.
Ideal for saving temporary data or stopping animations.
The activity is still partially visible but not interactive.

onStop():
Called when the activity is no longer visible.
Useful for releasing resources or saving data.

onRestart():
Called when the activity is coming back to the foreground from a stopped state.

onDestroy():
Called before the activity is destroyed.
Ideal for cleanup tasks.
 */
