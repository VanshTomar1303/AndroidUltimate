package com.vansh.animation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateInt
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableTarget
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vansh.animation.ui.theme.AnimationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationTheme {
                AnimationVisible()
            }
        }
    }
}

@Composable
fun AnimationVisible(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var isVisible by remember{
            mutableStateOf(false)
        }

        Button(
            onClick = {
                isVisible = !isVisible
            }
        ) {
            Text(text = "Toggle")
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally() + fadeIn(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier.background(Color.Red))
        }
    }
}

@Composable
fun AnimateAnyValue(){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var isVisible by remember{
            mutableStateOf(false)
        }
        var isRound by remember{
            mutableStateOf(false)
        }

        Button(
            onClick = {
                isVisible = !isVisible
                isRound = !isRound
            }
        ) {
            Text(text = "Toggle")
        }
        val transition = updateTransition(
            targetState = isRound,
            label = null
        )

//        val borderRadius by animateIntAsState(
//            targetValue = if(isRound) 100 else 0,
//            animationSpec = tween(
//                durationMillis = 3000,
//                delayMillis = 500
//            ) // commonly used
//        )

        val borderRadius by transition.animateInt(
            transitionSpec = { tween(2000)},
            label = "borderRadius",
            targetValueByState = {
                isRound -> if(isRound) 100 else 0
            }
        )

        val color by transition.animateColor(
            transitionSpec = { tween(1000)},
            label = "color",
            targetValueByState = {
                    isRound -> if(isRound) Color.Green else Color.Red
            }
        )
        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(borderRadius))
                .background(color)
        )

    }
}

@Composable
fun InfiniteAnimation() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var isVisible by remember{
            mutableStateOf(false)
        }

        Button(
            onClick = {
                isVisible = !isVisible
            }
        ) {
            Text(text = "Toggle")
        }

        val repeatable = rememberInfiniteTransition()
        val color by repeatable.animateColor(
            initialValue = Color.Red,
            targetValue = Color.Green,
            animationSpec = infiniteRepeatable(
                animation = tween(2000),
                repeatMode = RepeatMode.Reverse
            )
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .background(color)
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimateContent() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        var isVisible by remember{
            mutableStateOf(false)
        }

        Button(
            onClick = {
                isVisible = !isVisible
            }
        ) {
            Text(text = "Toggle")
        }

        AnimatedContent(
            targetState = isVisible,
            modifier = Modifier.fillMaxWidth()
                .weight(1f),
            content = { isVisible ->
                if (isVisible){
                    Box(modifier = Modifier.background(Color.Green))
                }else {
                    Box(modifier = Modifier.background(Color.Red))
                }
            },
            transitionSpec = {
                //fadeIn() with fadeOut()
                slideInHorizontally(
                    initialOffsetX = {
                        //-it
                        if(isVisible) -it else it
                    }
                ) with slideOutHorizontally(
                    targetOffsetX = {
                        //it
                        if(isVisible) it else -it
                    }
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Animation() {
    //AnimationVisible()
    //AnimateAnyValue()
    //InfiniteAnimation()
    AnimateContent()
}
