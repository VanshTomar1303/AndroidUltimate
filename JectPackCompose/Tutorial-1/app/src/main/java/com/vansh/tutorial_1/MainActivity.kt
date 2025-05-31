package com.vansh.tutorial_1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vansh.tutorial_1.ui.theme.Tutorial1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tutorial1Theme {
                FirstUi()
            }
        }
    }

    @Composable
    fun FirstUi(){
        Button(
            onClick = {
                Toast.makeText(this,"Hello", Toast.LENGTH_SHORT).show()
            }
        ) {
            Text(
                text = "Hello"
            )
        }
    }
    @Preview
    @Composable
    fun Preview(){
        FirstUi()
    }
}