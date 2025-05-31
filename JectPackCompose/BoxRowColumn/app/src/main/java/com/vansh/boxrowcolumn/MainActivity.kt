package com.vansh.boxrowcolumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.vansh.boxrowcolumn.ui.theme.BoxRowColumnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BoxRowColumnTheme {
                SecondUi()
            }
        }
    }
    @Composable
    fun SecondUi(){
        Row(
            modifier = Modifier.fillMaxWidth()
                .background(MaterialTheme.colorScheme.primaryContainer),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {}
            ) {
                Text(text = "click me 1")
            }
            Button(
                onClick = {}
            ) {
                Text(text = "click me 2")
            }
        }
        Column(
            modifier = Modifier.fillMaxHeight(0.5f)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {}
            ) {
                Text(text = "click me 3")
            }
            Button(
                onClick = {}
            ) {
                Text(text = "click me 4")
            }
        }
        Box(
           modifier = Modifier.fillMaxHeight(0.3f)
               .background(MaterialTheme.colorScheme.background)
        ){
            Button(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {}
            ) {
                Text(text = "click me 3")
            }
            Button(
                modifier = Modifier.align(Alignment.CenterEnd),
                onClick = {}
            ) {
                Text(text = "click me 4")
            }
        }

    }

    @Preview
    @Composable
    fun Preview(){
        SecondUi()
    }
}

// Box ->
// Column -> column set elements in a column.
// Row -> row set elements in a row.
