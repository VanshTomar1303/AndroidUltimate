package com.vansh.intentandintentfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.vansh.intentandintentfilter.ui.theme.IntentAndIntentFilterTheme

class SecondActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentAndIntentFilterTheme {
                Text(
                    text = "Second Activity",
                    textAlign = TextAlign.Center,
                    fontSize = 34.sp
                )
            }
        }
    }
}