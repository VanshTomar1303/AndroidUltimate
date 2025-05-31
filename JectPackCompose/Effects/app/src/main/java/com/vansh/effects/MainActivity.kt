package com.vansh.effects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import com.vansh.effects.ui.theme.EffectsTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private var i = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
//            var text by remember {
//                mutableStateOf("")
//            }
            EffectsTheme {
//                Button(onClick = { text+="#" }) {
//                    i++ // if button recompose it call every time when it recompose.
//                    Text(text = text)
//                }
//                LaunchedEffect(key1 = text) {
//                    delay(1000L)
//                    println("The text is $text")
//                }

//                val scope = rememberCoroutineScope()
//                Button(
//                    onClick = {
//                        scope.launch{
//                            delay(1000)
//                            println("Hello")
//                        }
//                    }
//                ) { }

//                val updatedOnTimeout by rememberUpdatedState(newValue = {})
//                LaunchedEffect(true) {
//                    delay(3000L)
//                    updatedOnTimeout()
//                }
            }
        }
    }
}
// side effect -> a operation that can effect ui but happen outside from the composable.
/*
 LaunchedEffect -> commonly used
        we can use suspended function init.
        it is a composable
        keys -> it can be a state
        '-> so if the key changes it will recompose it body every time it changes.
 */
/*
    rememberCoroutineScope -> it only uses we make callbacks
    it not often in need
 */
/*
    rememberUpdatedState -> Imagine you have a magic toy box that remembers your favorite toy, even if you leave the room and come back. When you put a toy in the box, it stays there, nice and safe, until you decide to change it.
 */
/*
    DisposableEffect -> commonly needed
    n Jetpack Compose, DisposableEffect works just like that music box. When you enter a screen or a part of your app, DisposableEffect starts doing something (like listening for changes, starting a timer, or connecting to a service). And when you leave or things change, it automatically stops and cleans up after itself, just like the music box turning off.
 */