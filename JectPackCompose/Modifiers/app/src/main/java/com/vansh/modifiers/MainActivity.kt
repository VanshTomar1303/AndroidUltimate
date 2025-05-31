package com.vansh.modifiers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vansh.modifiers.ui.theme.ModifiersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModifiersTheme {
                ThirdUi()
            }
        }
    }

    @Composable
    fun ThirdUi(){
        Row(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(10.dp))// it has to be written in same manner
                .border(20.dp, Color.Blue)
                .background(MaterialTheme.colorScheme.onBackground)
                .verticalScroll(rememberScrollState()) // to make scroll
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)// push element to center
                //.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Button(
                modifier = Modifier
                    .offset(20.dp), // offset is like a margin
                onClick = {},
            ) {
                Text(
                    text = "ありがと"
                )
            }
            Spacer(modifier = Modifier.width(40.dp)) // we can use width or height to give space
            Button(
                modifier = Modifier
                    .offset(20.dp), // offset is like a margin
                onClick = {},
            ) {
                Text(
                    text = "あいたい"
                )
            }
        }
    }
    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        ThirdUi()
    }
}
// modifier ->  modifier is like to design a element and set's it height, width any many more things like that.
// spacer -> it used to give space b/w composable
