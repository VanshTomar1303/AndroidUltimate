package com.vansh.images

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.vansh.images.ui.theme.ImagesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ImagesTheme {
                Images()
            }
        }
    }
}

@Composable
fun Images() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        val imageUrl = "https://i.pinimg.com/736x/97/ad/cf/97adcf87ddfc2dbda588164775416b70.jpg"

        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .aspectRatio(16/9f)
                .rotate(-90f),
            painter = painterResource(R.drawable.zerotwo),
            contentDescription = "02",
        )
        val image = ImageRequest
            .Builder(LocalContext.current)
            .data(imageUrl)
            .size(coil.size.Size.ORIGINAL)
            .build()

        AsyncImage(
            model = image,
            contentDescription = "hero",
        )

        val imageState = rememberAsyncImagePainter(model = image).state

        // simpler way to write it
        when(imageState){
            is AsyncImagePainter.State.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(200.dp),
                    color = Color.Green
                )
            }
            is AsyncImagePainter.State.Success -> {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .aspectRatio(16/9f)
                        .rotate(-90f),
                    painter = imageState.painter,
                    contentDescription = "02",
                )
            }
            else -> {
                Icon(
                    imageVector = Icons.Filled.BrokenImage,
                    contentDescription = "Image Not Found"
                )
            }
        }

//        if(imageState is AsyncImagePainter.State.Success){
//            Image(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp)
//                    .aspectRatio(16/9f)
//                    .rotate(-90f),
//                painter = imageState.painter,
//                contentDescription = "02",
//            )
//        }
//        if(imageState is AsyncImagePainter.State.Loading){
//            CircularProgressIndicator(
//                modifier = Modifier.size(200.dp),
//                color = Color.Green
//            )
//        }
//        if(imageState is AsyncImagePainter.State.Error){
//            Icon(
//                imageVector = Icons.Filled.BrokenImage,
//                contentDescription = "Image Not Found"
//            )
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Images()
}

// to use a image from browser or by an link we have a dependency
// implementation("io.coil-kt:coil-compose:2.4.0")
// to access more icon we have a dependency
// implementation("androidx.compose.material:material-icons-extended:1.6.2")