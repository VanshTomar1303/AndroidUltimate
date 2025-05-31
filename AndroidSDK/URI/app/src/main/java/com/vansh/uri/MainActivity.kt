package com.vansh.uri

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import com.vansh.uri.ui.theme.URITheme

/*
    URI (Unique Resource Identifier) :
        * Path to specific resource.

        Type of URI :
            1. Resource Uri:
                It use for res folder data.
                val uri = Uri.parse("android.resource://$packageName/drawable/<filename>")
                val uriByte = contentResolver.openInputStream(uri)?use.{
                        it.readBytes()
                }
                println("Uri Size: ${uriByte?.size}")

             2. File Uri:
                val file = File(fileDir,"null.png")
                FileOutputStream(file).use{
                    it.write(uriByte)
                }
                println(file.toUri())
                
             3. Content Uri:
                val pickImage = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { contentUri ->
                        println(contentUri)
                    }
                )
                Button(onClick = {
                    pickImage.launch("image/<*>")
                }) {
                    Text(text = "pickImage")
                }

              4. Data Uri:
                    val dataUri = Uri.parse("data:text/plain;charset=UTF-8,Hello%20World")
             
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            URITheme {
                val pickImage = rememberLauncherForActivityResult(
                    contract = ActivityResultContracts.GetContent(),
                    onResult = { contentUri ->
                        println(contentUri)
                    }
                )
                Button(onClick = {
                    pickImage.launch("image/*")
                }) {
                    Text(text = "pickImage")
                }
            }
        }
    }
}

