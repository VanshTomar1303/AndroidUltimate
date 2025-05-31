package com.vansh.textstyle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.vansh.textstyle.ui.theme.TextStyleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TextStyleTheme {
                TextStyles()
            }
        }
    }
}

@Composable
fun TextStyles() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val shadowoffset = Offset(5f,10f)

        Text(
            text = buildAnnotatedString {
                append("Some text is")
                withStyle(
                    SpanStyle(
                       color = Color.Red,
                        fontWeight = FontWeight.ExtraBold
                    )
                ){
                    append("written")
                }
                withStyle(
                    SpanStyle(
                        color = Color.Blue,
                        textDecoration = TextDecoration.LineThrough
                    )
                ){
                    append("YES")
                }
            },
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.primary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 1.sp,
            overflow = TextOverflow.Ellipsis,
            textDecoration = TextDecoration.Underline,
            maxLines = 2,
            style = TextStyle(
                shadow = Shadow(
                    color = Color.Gray,
                    offset = shadowoffset,
                    blurRadius = 9f
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TextStyleTheme {
        TextStyles()
    }
}