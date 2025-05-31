package com.vansh.composestateandremember

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vansh.composestateandremember.ui.theme.ComposeStateAndRememberTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeStateAndRememberTheme {

            }
        }
    }
}

@Composable
fun Screen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // to control behaviors
        val text = remember{ // it remember the value of it state
            mutableStateOf(true) // it store values in it
        }

        val textValue = if (text.value) "Visible" else "Not Visible"
        // 1 trick to do it
//        if (text.value){
//            Text(
//            text = "Visible Text"
//            )
//        }
        // 2 trick to do it
        Text(
            modifier = Modifier.alpha(if (text.value)1f else 0.3f),
            text = textValue
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                text.value = !text.value
            }
        ) {
            Text(text = "Change Text visibility")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeStateAndRememberTheme {
        Screen()
    }
}