package com.vansh.textfields

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text2.BasicTextField2
import androidx.compose.foundation.text2.input.TextFieldLineLimits
import androidx.compose.foundation.text2.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vansh.textfields.ui.theme.TextFieldsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextFieldsTheme {
                TextFields()
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TextFields() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var textInput by remember {
            mutableStateOf("")
        }

        val textstate = rememberTextFieldState()

        TextField(
            value = textInput,
            onValueChange = {
                newText -> textInput = newText
            },
            label = {
                Text(text = "Type")
            }
        )
        OutlinedTextField(
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp)),
            value = textInput,
            onValueChange = {
                    newText -> textInput = newText
            },
            label = {
                Text(text = "Type")
            },
            textStyle = TextStyle(
                color = Color.Blue
            ),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            },
            trailingIcon = {
                if (textInput.isNotEmpty()){
                    IconButton(
                        onClick = {
                            textInput = ""
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "delete"
                        )
                    }
                }
            }
        )

        // in basic textfield we have to define everything
        BasicTextField(
            modifier = Modifier.background(MaterialTheme.colorScheme.error)
                .fillMaxWidth(),
            value = textInput,
            onValueChange = {
                textInput = it
            }
        )

        BasicTextField2(
            state = textstate,
            lineLimits = TextFieldLineLimits.SingleLine
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextFieldsTheme {
        TextFields()
    }
}