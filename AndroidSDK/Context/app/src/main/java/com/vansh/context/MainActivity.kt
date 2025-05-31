package com.vansh.context

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val context: Context = this
        setContent {

        }
    }
}


/*
    Context -> it refer to bridge between your app and the android os
    Context is like the heart of your Android app. It gives you access to everything inside your app and the Android system — like:

your app’s files and resources (images, strings, etc.),

starting a new screen (Activity),

showing messages (like Toasts),

and asking Android for services (like Internet, Location, etc.)
 */